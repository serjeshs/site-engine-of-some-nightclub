package by.havefun;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class GlobalSettings {
	
	
	public static final String fileSeparator = System.getProperty("file.separator");
	public static final String folderFilesTemp = System.getProperty("java.io.tmpdir");
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	
	
	public static final String folderFiles;
	/** 
	 * Если это продакшен, то нужно всё хранить в определённом каталоге, иначе в темповой дериктории. 
	 */
	static {
		String path = "/home/sites/barabash/afisha/images";
		File file = new File(path);
		if (file.isDirectory()) {
			folderFiles = path;
		} else {
			folderFiles = System.getProperty("java.io.tmpdir");
		}
		
	}
}
