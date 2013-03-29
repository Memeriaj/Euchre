
public class Utils {

	static String convertStringToHTML(String regularString) {
		return "<html>" + regularString.replaceAll("\n", "<br>") + "</html>";
	}
}
