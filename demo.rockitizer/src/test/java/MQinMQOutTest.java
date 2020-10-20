import org.apache.log4j.Logger;
import org.junit.Test;
import org.xmlunit.diff.ElementSelectors;

import com.google.common.collect.ImmutableList;
import com.rockit.common.blackboxtester.assertions.XMLFileAssertion;
import com.rockit.common.blackboxtester.suite.structures.TestBuilder;
import com.rockit.common.blackboxtester.wrapper.AbstractTestWrapper;

public class MQinMQOutTest extends AbstractTestWrapper {
	public static Logger logger = Logger.getLogger(MQinMQOutTest.class.getName());
	public TestBuilder testBuilder = newTestBuilderFor(MQinMQOutTest.class);

	@Test
	public void testAddMsgToMQFlow() throws Exception {

		// write messages to inputQueue and wait
		testBuilder.addStep("010MQPutMessage").execute().sleep(5000);

		// read messages from queue after waiting and error
		testBuilder.addStep("020MQGetError").execute();
		testBuilder.addStep("020MQGETMessage").execute();

		// write messages to inputQueue and wait
		testBuilder.addStep("030MQPutMessage").execute().sleep(5000);

		// read messages from queue after waiting and error
		testBuilder.addStep("040MQGetError").execute();
		testBuilder.addStep("040MQGETMessage").execute();

		// write messages to inputQueue and wait
		testBuilder.addStep("050MQPutMessage").execute().sleep(5000);

		// read messages from queue after waiting and error
		testBuilder.addStep("060MQGetError").execute();
		testBuilder.addStep("060MQGETMessage").execute();

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("020MQGETMessage").withNodeMatcher(ElementSelectors.byNameAndText)
		// .ignoreAttrs(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","processingTime"))
				.ignore(ImmutableList.of("msgId")).checkForSimilar());

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("040MQGETMessage").withNodeMatcher(ElementSelectors.byNameAndText)
		// .ignoreAttrs(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","processingTime"))
		// .ignore(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","beginTimestamp", "endTimestamp",
		// "addressDate","businessConnectionDate"))
				.checkForSimilar());

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("060MQGETMessage").withNodeMatcher(ElementSelectors.byNameAndText)
		// .ignoreAttrs(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","processingTime"))
		// .ignore(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","beginTimestamp", "endTimestamp",
		// "addressDate","businessConnectionDate"))
				.checkForSimilar());
	}

}
