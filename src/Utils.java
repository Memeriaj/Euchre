
public class Utils {

	static String convertStringToHTML(String regularString) {
		String out = regularString.replaceAll("\n", "<br>");
		out = out.replaceAll("  ", " &nbsp");
		return "<html>" + out + "</html>";
	}
}
