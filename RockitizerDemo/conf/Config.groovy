/*
*	Copyright 2015 rockit.consulting GbR  (www.rockit.consulting)
*
*/
  
   
 
/* Uncomment if you have application */   
deployApplicationName='RockitizerDemo'
  
    
projectToBar = [
		['*':['RockitizerDemo']],

]




forceFlowExclude = []
    

testProjectDir='C:\\temp\\test'    
    

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

	jenkins { //decoupled qmgr
		
		brokerProjectDirs=[
			'/var/jenkins_home/workspace/RockitizerDemo.github/RockitizerDemo/'
		]
		
   	     barToExecutionGroup {
  	        RockitizerDemo.bar=['default']
  	      }
    
  		
    }
  
}



