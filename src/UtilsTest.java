import static org.junit.Assert.*;

import org.junit.Test;


public class UtilsTest {

	@Test
	public void convertStringToHTML_emptyString_HTMLTags() {
		String toTest = "";
		String expected = "<html></html>";
		String output = Utils.convertStringToHTML(toTest);
		assertTrue(expected.matches(output));
	}

	@Test
	public void convertStringToHTML_simpleStringNoNewlines_HTMLTagsAndString() {
		String toTest = "This is a simple String";
		String expected = "<html>This is a simple String</html>";
		String output = Utils.convertStringToHTML(toTest);
		assertTrue(expected.matches(output));
	}

	@Test
	public void convertStringToHTML_simpleStringOneNewlines_HTMLTagsStringBreak() {
		String toTest = "This is a \n simple String";
		String expected = "<html>This is a <br> simple String</html>";
		String output = Utils.convertStringToHTML(toTest);
		assertTrue(expected.matches(output));
	}

	@Test
	public void convertStringToHTML_onlyNewlines_HTMLTagsBreaks() {
		String toTest = "\n\n\n\n\n";
		String expected = "<html><br><br><br><br><br></html>";
		String output = Utils.convertStringToHTML(toTest);
		assertTrue(expected.matches(output));
	}

}
