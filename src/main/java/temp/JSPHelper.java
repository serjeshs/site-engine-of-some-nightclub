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
	///////

}
