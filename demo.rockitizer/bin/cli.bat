@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=
if exist "%BASEDIR%\bin\set-env.bat" call "%BASEDIR%\bin\set-env.bat"

if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\test.rockitizer-1.0.4.2.jar;"%REPO%"\ojdbc10_g-19.3.0.0.jar;"%REPO%"\ojdbc10dms-19.3.0.0.jar;"%REPO%"\ojdbc10dms_g-19.3.0.0.jar;"%REPO%"\dms-19.3.0.0.jar;"%REPO%"\db2jcc-db2jcc4.jar;"%REPO%"\com.ibm.mq.allclient-9.1.4.0.jar;"%REPO%"\bcprov-jdk15on-1.64.jar;"%REPO%"\bcpkix-jdk15on-1.64.jar;"%REPO%"\javax.jms-api-2.0.1.jar;"%REPO%"\snakeyaml-1.25.jar;"%REPO%"\junit-4.11.jar;"%REPO%"\hamcrest-core-1.3.jar;"%REPO%"\log4j-1.2.17.jar;"%REPO%"\httpclient-4.5.jar;"%REPO%"\httpcore-4.4.1.jar;"%REPO%"\commons-logging-1.2.jar;"%REPO%"\commons-codec-1.9.jar;"%REPO%"\xmlunit-core-2.1.0.jar;"%REPO%"\guava-27.0-jre.jar;"%REPO%"\failureaccess-1.0.jar;"%REPO%"\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;"%REPO%"\jsr305-3.0.2.jar;"%REPO%"\checker-qual-2.5.2.jar;"%REPO%"\error_prone_annotations-2.2.0.jar;"%REPO%"\j2objc-annotations-1.1.jar;"%REPO%"\animal-sniffer-annotations-1.17.jar;"%REPO%"\jsch-0.1.54.jar;"%REPO%"\commons-io-2.4.jar;"%REPO%"\json-20160810.jar;"%REPO%"\commons-text-1.8.jar;"%REPO%"\commons-lang3-3.9.jar;"%REPO%"\picocli-4.2.0.jar;"%REPO%"\jansi-1.18.jar;"%REPO%"\demo.rockitizer-0.0.1-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="cli" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" io.github.rockitconsulting.test.rockitizer.cli.RockitizerCLI %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
