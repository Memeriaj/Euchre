import java.util.Locale;
import java.util.ResourceBundle;


public class Utils {

	static String convertStringToHTML(String regularString) {
		String out = regularString.replaceAll("\n", "<br>");
		out = out.replaceAll("  ", " &nbsp");
		return "<html>" + out + "</html>";
	}
	
	static String internationalizeString(String s){
		if(s.indexOf(' ') != -1){
			String out = "";
			for(String sub : s.split(" "))
				out += internationalizeString(sub)+" ";
			return out.substring(0, out.length()-1);
		}
		System.out.println(s);
		Locale loc = new Locale("en", "US");
		ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", loc);
		return messages.getString(s);
	}
	
	static String internationalizeString(String s, Locale l){
		System.out.println(s);
		ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", l);
		return messages.getString(s);
	}
}
