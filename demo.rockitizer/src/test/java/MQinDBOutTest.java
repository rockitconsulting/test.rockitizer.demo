
import org.apache.log4j.Logger;
import org.junit.Test;
import org.xmlunit.diff.ElementSelectors;

import com.rockit.common.blackboxtester.assertions.XMLFileAssertion;
import com.rockit.common.blackboxtester.suite.structures.TestBuilder;
import com.rockit.common.blackboxtester.wrapper.AbstractTestWrapper;

public class MQinDBOutTest extends AbstractTestWrapper {
	public static Logger logger = Logger.getLogger(MQinDBOutTest.class.getName());
	public TestBuilder testBuilder = newTestBuilderFor(MQinDBOutTest.class);

	@Test
	public void testMQInMQOutFlow() throws Exception {

		// write messages to inputQueue and wait
		testBuilder.addStep("010MQPutMessage").execute().sleep(3000);

		// read messages from queue after waiting and error
		testBuilder.addStep("020DBGetMessage").execute();
		
		testBuilder.addStep("030MQGetError").execute();

		// do assertion with recorded values if mode is reply
		testBuilder.addAssertion(new XMLFileAssertion("020DBGetMessage").withNodeMatcher(ElementSelectors.byNameAndText)
		// .ignoreAttrs(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","processingTime"))
		// .ignore(ImmutableList.of("updateTimestamp",
		// "insertTimestamp","beginTimestamp", "endTimestamp",
		// "addressDate","businessConnectionDate"))
				.checkForSimilar());
	}
}
