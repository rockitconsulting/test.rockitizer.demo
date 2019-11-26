package com.rockit.tools.test.demo;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.xmlunit.diff.ElementSelectors;

import com.rockit.common.blackboxtester.assertions.XMLFileAssertion;
import com.rockit.common.blackboxtester.suite.structures.TestBuilder;
import com.rockit.common.blackboxtester.wrapper.AbstractTestWrapper;


public class MQinDBoutTest extends AbstractTestWrapper {
	public static Logger logger = Logger.getLogger(MQinDBoutTest.class.getName()); 
	public TestBuilder testBuilder = newTestBuilderFor(MQinDBoutTest.class);


	@Test
	public void testMQInMQOutFlow() throws Exception {
		
		//write messages to inputQueue and wait
		testBuilder.addStep("a001MQPutMessage").execute().sleep(3000);
		
		//read messages from queue after waiting and error
		testBuilder.addStep("a002DBGetMessage").execute();
		testBuilder.addStep("a002MQGetError").execute();
		
		
		// do assertion with recorded values if mode is reply
				testBuilder.addAssertion(new XMLFileAssertion("a002DBGetMessage")
						.withNodeMatcher(ElementSelectors.byNameAndText)
						//.ignoreAttrs(ImmutableList.of("updateTimestamp", "insertTimestamp","processingTime"))
						//.ignore(ImmutableList.of("updateTimestamp", "insertTimestamp","beginTimestamp", "endTimestamp", "addressDate","businessConnectionDate"))
						.checkForSimilar());
	}
}
