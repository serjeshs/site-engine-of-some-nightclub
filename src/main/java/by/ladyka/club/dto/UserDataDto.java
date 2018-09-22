package by.ladyka.club.dto;

import lombok.Data;

@Data
public class UserDataDto {
	String appCodeName;
	String appName;
	String appVersion;
	boolean cookieEnabled;
	String language;
	boolean onLine;
	String platform;
	int seconds;
	String userAgent;
}
