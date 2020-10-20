import org.apache.log4j.Logger;
import org.junit.Test;
import org.xmlunit.diff.ElementSelectors;

import com.google.common.collect.ImmutableList;
import com.rockit.common.blackboxtester.assertions.XMLFileAssertion;
import com.rockit.common.blackboxtester.suite.structures.TestBuilder;
import com.rockit.common.blackboxtester.wrapper.AbstractTestWrapper;

public class MQinFILEOutTest extends AbstractTestWrapper {
	public static Logger logger = Logger.getLogger(MQinFILEOutTest.class.getName());
	public TestBuilder testBuilder = newTestBuilderFor(MQinFILEOutTest.class);

	@Test
	public void testMQInFileOutFlow() throws Exception {

		// write messages to inputQueue and wait
		testBuilder.addStep("010MQPutMessage").execute().sleep(3000);

		// read messages from directory after waiting and error
		testBuilder.addStep("020FILEGetMessage").execute();

		testBuilder.addStep("030MQGetError").execute();

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("020FILEGetMessage").withNodeMatcher(ElementSelectors.byNameAndText)
				.ignoreAttrs(ImmutableList.of("uuid"))
				// .ignore(ImmutableList.of("updateTimestamp",
				// "insertTimestamp", "beginTimestamp", "endTimestamp",
				// "addressDate", "businessConnectionDate"))
				.checkForIdentical());
	}
}
