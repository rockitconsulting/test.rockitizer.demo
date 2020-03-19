[![N|Solid](http://www.rockit.consulting/images/logo-fixed.png)](http://www.rockit.consulting)

# Structure
Demo IIB project **RockitizerDemo** 

Demo Test.Rockitizer project **demo.rockitizer**

# Mission

**demo.rockitizer** was designed as a reference test suite, to demonstrate interaction of the Test.Rockitizer test framework with a real-life integration project. 
**demo.rockitizer** disposes of ready-made test plans, engineered to demonstrate testing techniques in combination with the **RockitizerDemo**. 

# RockitizerDemo 
***Integration application with various connectors. Written for IBM Integration Bus/AppConnect***
##Configuration
*conf/demo.broker* - integration node configuration details

*conf/MQMON.CFG* - MO72 MQ client configuration details




# demo.rockitizer 
***Off-the-shelf test suite. Optimized for testing the RocktizerDemo*** 

**FILEInFILEOutTEST** - testing *File2File.msgflow* via FILEPUT/FILEGET and FILEDEL connectors;

**FILEInMQOutTEST** - testing *File2MQ.msgflow* via FILEPUT and MQGET;

**JSONwithDatabaseTEST** - testing *JSON2DB.msgflow* via DBPUT/DBGET, MQGET and HTTP;

**MQInDBOutTEST** - testing *MQ2DB.msgflow* via DBPUT/DBGET and MQPUT/MQGET;

**MQInMQOutTEST** - testing *MQ2MQ.msgflow* via MQPUT/MQGET;

**WSWithDatabaseTEST** - testing *WSwithDatabase.msgflow* via DBPUT/DBGET, HTTP and MQGET connectors.

## Authors

`test.rockitizer` was created by [`Yefym Dmukh`](https://github.com/yefymdmukh).

`test.rockitizer` is sponsored by [`rockit.consulting GmbH`](http://www.rockit.consulting/)

## License
GNU General Public License v3.0 or later

See [LICENSE](LICENSE.md) to see the full text.

## Publications
[IBM Integration Community: junit based integration testing](https://developer.ibm.com/integration/blog/2017/08/29/junit-based-integration-testing-ibm-integration-bus/).
