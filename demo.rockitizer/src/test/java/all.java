import org.apache.log4j.Logger;
import org.junit.Test;
import org.xmlunit.diff.ElementSelectors;
 
import com.google.common.collect.ImmutableList;
import com.rockit.common.blackboxtester.assertions.XMLFileAssertion;
import com.rockit.common.blackboxtester.suite.structures.TestBuilder;
import com.rockit.common.blackboxtester.wrapper.AbstractTestWrapper;
 
public class all extends AbstractTestWrapper {
	public static Logger logger = Logger.getLogger(all.class.getName());
	public TestBuilder testBuilder = newTestBuilderFor(all.class);
 
	@Test
	public void testall() throws Exception {
 
 
	}
}
