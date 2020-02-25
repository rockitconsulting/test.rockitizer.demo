
import org.apache.log4j.Logger;
import org.junit.Test;
import org.xmlunit.diff.ElementSelectors;

import com.google.common.collect.ImmutableList;
import com.rockit.common.blackboxtester.assertions.XMLFileAssertion;
import com.rockit.common.blackboxtester.suite.structures.TestBuilder;
import com.rockit.common.blackboxtester.wrapper.AbstractTestWrapper;

public class WSwithDatabaseTest extends AbstractTestWrapper {
	public static Logger logger = Logger.getLogger(WSwithDatabaseTest.class.getName());
	public TestBuilder testBuilder = newTestBuilderFor(WSwithDatabaseTest.class);

	@Test
	public void testAddBookServiceFlow() throws Exception {

		// write messages to inputQueue and wait
		// ,testBuilder.addStep("a001MQPutMessage").execute().sleep(3000);

		// read messages from queue after waiting and error
		testBuilder.addStep("a001WSPutMessage").execute();
		testBuilder.addStep("a002DBGETMessage").execute();

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("a001WSPutMessage").withNodeMatcher(ElementSelectors.byNameAndText)
		// .ignoreAttrs(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","processingTime"))
				.ignore(ImmutableList.of("ID")).checkForSimilar());

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("a002DBGETMessage").withNodeMatcher(ElementSelectors.byNameAndText)
		// .ignoreAttrs(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","processingTime"))
		// .ignore(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","beginTimestamp", "endTimestamp",
		// "addressDate","businessConnectionDate"))
				.checkForSimilar());
	}
}
