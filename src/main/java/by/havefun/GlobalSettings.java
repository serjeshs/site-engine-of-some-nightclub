package by.havefun;

import java.time.format.DateTimeFormatter;

public class GlobalSettings {
	
	
	public static String fileSeparator = System.getProperty("file.separator");
	public static String folderFiles = System.getProperty("java.io.tmpdir");
	//public static String folderFiles = "C:\\promofiles";
	public static String folderFilesTemp = System.getProperty("java.io.tmpdir");
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
