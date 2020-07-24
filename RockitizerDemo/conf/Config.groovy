/*
*	Copyright 2015 rockit.consulting GbR  (www.rockit.consulting)
*
*/
  
   
 
/* Uncomment if you have application 
   for multibar use: 
   deployApplicationName='RockitizerDemo SecondAppForMultibar'
*/   
deployApplicationName='RockitizerDemo'
  
    
projectToBar = [
	        ['*':['RockitizerDemo']],

]




forceFlowExclude = []
    

testProjectDir='C:\\temp\\test\\'    
    

broker = 'demo.broker'
mqcfg = 'MQMON.CFG'





environments {

    demo { //decoupled qmgr
      queueManager {
	    	 name='QM1' 
	    	 host='localhost'
	    	 listenerPort='1414'
	    	 svrconn='SYSTEM.BKR.CONFIG'
      }
		
	 brokerProjectDirs=[
			'C:\\rockit\\projects\\github\\test.rockitizer.demo\\'
			]
		
        barToExecutionGroup {
          RockitizerDemo.bar=['default']
        }
    
  		
    }
  
}



