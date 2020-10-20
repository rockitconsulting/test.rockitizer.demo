
import org.apache.log4j.Logger;
import org.junit.Test;
import org.xmlunit.diff.ElementSelectors;

import com.google.common.collect.ImmutableList;
import com.rockit.common.blackboxtester.assertions.XMLFileAssertion;
import com.rockit.common.blackboxtester.suite.structures.TestBuilder;
import com.rockit.common.blackboxtester.wrapper.AbstractTestWrapper;

public class FILEinFILEOutTest extends AbstractTestWrapper {
	public static Logger logger = Logger.getLogger(FILEinFILEOutTest.class.getName());
	public TestBuilder testBuilder = newTestBuilderFor(FILEinFILEOutTest.class);

	@Test
	public void testFILEinFILEoutFlow() throws Exception {

		// write messages to inputQueue and wait
		testBuilder.addStep("010FILEPutMessage").execute().sleep(10000);

		// read messages from queue after waiting and error
		testBuilder.addStep("020FILEGetMessage").execute();

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("020FILEGetMessage").withNodeMatcher(ElementSelectors.byNameAndText)
				.ignoreAttrs(ImmutableList.of("updateTimestamp", "insertTimestamp", "processingTime"))
				.ignore(ImmutableList.of("updateTimestamp", "insertTimestamp", "beginTimestamp", "endTimestamp", "addressDate", "businessConnectionDate"))
				.checkForSimilar());
	}
}
