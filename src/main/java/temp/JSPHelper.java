package temp;

public class JSPHelper {
	
	public static String getColumnPosition(int a) {
		String ret = "ERROR";
	    switch (a) {
		case 0:
			ret = "grid_4 alpha";
			break;
		case 1:
			ret = "grid_4";
			break;
		case 2:
			ret = "grid_4 omega";
			break;
		}
	    return ret;    
    }
	
	public static String getInputlabel(String text, String field, String type) {
		return "<label><input type=\"" + type + "\" value=\"" + text + "\"  name=\"" + field + "\" onFocus=\"if(this.value=='" + text +"'){this.value=''}\" onBlur=\"if(this.value==''){this.value='" + text +"'}\">	</label>";
	}
	
	public static String getInputlabel(String text, String field) {
		return getInputlabel(text, field,"text");
	}
	
	public static String getTextArea(String text, String field) {
		return "<textarea name=\"" + field + "\" onFocus=\"if(this.value=='" + text + "'){this.value=''}\" onBlur=\"if(this.value==''){this.value='" + text + "'}\">" + text + "</textarea><br><br>";
	}
}
