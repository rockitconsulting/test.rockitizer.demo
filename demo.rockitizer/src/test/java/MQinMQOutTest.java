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
		testBuilder.addStep("a001MQPutMessage").execute().sleep(5000);

		// read messages from queue after waiting and error
		testBuilder.addStep("a002MQGetError").execute();
		testBuilder.addStep("a002MQGETMessage").execute();

		// write messages to inputQueue and wait
		testBuilder.addStep("a003MQPutMessage").execute().sleep(5000);

		// read messages from queue after waiting and error
		testBuilder.addStep("a004MQGetError").execute();
		testBuilder.addStep("a004MQGETMessage").execute();

		// write messages to inputQueue and wait
		testBuilder.addStep("a005MQPutMessage").execute().sleep(5000);

		// read messages from queue after waiting and error
		testBuilder.addStep("a006MQGetError").execute();
		testBuilder.addStep("a006MQGETMessage").execute();

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("a002MQGETMessage").withNodeMatcher(ElementSelectors.byNameAndText)
		// .ignoreAttrs(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","processingTime"))
				.ignore(ImmutableList.of("msgId")).checkForSimilar());

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("a004MQGETMessage").withNodeMatcher(ElementSelectors.byNameAndText)
		// .ignoreAttrs(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","processingTime"))
		// .ignore(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","beginTimestamp", "endTimestamp",
		// "addressDate","businessConnectionDate"))
				.checkForSimilar());

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("a006MQGETMessage").withNodeMatcher(ElementSelectors.byNameAndText)
		// .ignoreAttrs(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","processingTime"))
		// .ignore(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","beginTimestamp", "endTimestamp",
		// "addressDate","businessConnectionDate"))
				.checkForSimilar());
	}

}
