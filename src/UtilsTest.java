
import static org.junit.Assert.*;

import java.util.Locale;

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
	
	@Test
	public void convertStringToHTML_twoSpaces_spaceAndExtraSpace(){
		String toTest = "  ";
		String expected = "<html> &nbsp</html>";
		String output = Utils.convertStringToHTML(toTest);
		assertTrue(expected.matches(output));
	}
	
	@Test
	public void convertStringToHTML_threeSpaces_spaceExtraSpaceAnotherSpace(){
		String toTest = "   ";
		String expected = "<html> &nbsp </html>";
		String output = Utils.convertStringToHTML(toTest);
		assertTrue(expected.matches(output));
	}
	
	@Test
	public void convertStringToHTML_fourSpaces_spaceExtraSpaceAnotherSpaceAndExtraSpace(){
		String toTest = "    ";
		String expected = "<html> &nbsp &nbsp</html>";
		String output = Utils.convertStringToHTML(toTest);
		assertTrue(expected.matches(output));
	}
	
	@Test
	public void testUtilInit(){
		assertNotNull(new Utils());
	}
	
	@Test
	public void testAllTestsInit(){
		new AllTests();
	}
	
	@Test
	public void testInternationalizeString(){
		assertEquals(Utils.internationalizeString("overallScore"),"Overall Score");
		assertEquals(Utils.internationalizeString("yours"),"Yours");
		assertEquals(Utils.internationalizeString("SPADES"),"Spades");
		assertEquals(Utils.internationalizeString("right"),"Right");
	}
	
	@Test
	public void testInternationalizeStringWithUSLocale(){
		Locale loc = new Locale("en", "US");
		assertEquals(Utils.internationalizeString("overallScore",loc),"Overall Score");
		assertEquals(Utils.internationalizeString("yours",loc),"Yours");
		assertEquals(Utils.internationalizeString("SPADES",loc),"Spades");
		assertEquals(Utils.internationalizeString("right",loc),"Right");
	}
}