package com.rockit.tools.test.demo;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.xmlunit.diff.ElementSelectors;

import com.google.common.collect.ImmutableList;
import com.rockit.common.blackboxtester.assertions.XMLFileAssertion;
import com.rockit.common.blackboxtester.suite.structures.TestBuilder;
import com.rockit.common.blackboxtester.wrapper.AbstractTestWrapper;

public class JSONwithDatabaseTest extends AbstractTestWrapper {
	public static Logger logger = Logger.getLogger(JSONwithDatabaseTest.class
			.getName());
	public TestBuilder testBuilder = newTestBuilderFor(JSONwithDatabaseTest.class);

	@Test
	public void testAddBookServiceFlow() throws Exception {

		// write messages to inputQueue and wait
		// ,testBuilder.addStep("a001MQPutMessage").execute().sleep(3000);

		// read messages from queue after waiting and error
		testBuilder.addStep("a001JSONPutMessage").execute();
		testBuilder.addStep("a002DBGETMessage").execute();
		
		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("a001JSONPutMessage")
				.withNodeMatcher(ElementSelectors.byNameAndText)
				.ignore(ImmutableList.of("CF-RAY", "ETag", "Access-Control-Allow-Origin", "Connection",
								"Set-Cookie", "Date", "Expect-CT", "X-Powered-By", "createdAt", "id", "ID"))
				.checkForSimilar());
		
		

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("a002DBGETMessage")
				.withNodeMatcher(ElementSelectors.byNameAndText)
				//.ignoreAttrs(ImmutableList.of("updateTimestamp", "insertTimestamp","processingTime"))
				//.ignore(ImmutableList.of("updateTimestamp", "insertTimestamp","beginTimestamp", "endTimestamp", "addressDate","businessConnectionDate"))
				.checkForSimilar());
	}
}
