/*
*	Copyright 2015 rockit.consulting GbR  (www.rockit.consulting)
*
*/
  
   
 
/* Uncomment if you have application 
   for multibar use: 
   deployApplicationName='RockitizerDemo SecondAppForMultibar'
*/   
deployApplicationName='RockitizerDemo, SecondAppForMultibar'
  
    
projectToBar = [
	        ['*':['RockitizerDemo']],
	        

]


forceFlowExclude = []
    

    
mqcfg = 'MQMON.CFG'

environments {


    demo { 
    	  //iib10+ broker file has no qmgr configuration
	      queueManager {
		    	 name='QM1' 
		    	 host='localhost'
		    	 listenerPort='1414'
		    	 svrconn='SYSTEM.BKR.CONFIG'
	      }
	      broker = 'demo.broker'
			
		  brokerProjectDirs=[
				'C:\\rockit\\projects\\github\\test.rockitizer.demo\\',
				'C:\\rockit\\workspaces\\rockitizer_demo'
		  ]
			
	      barToExecutionGroup {
	          RockitizerDemo.bar=['default']
	      }
    }

	jenkins { //packaging only
		
		brokerProjectDirs=[
			'/var/jenkins_home/workspace/RockitizerDemo.github/'
		]
		
   	     barToExecutionGroup {
  	        RockitizerDemo.bar=['default']
  	      }
    
  		
    }
  
}



