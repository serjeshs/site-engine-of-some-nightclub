package by.ladyka.bepaid.logger;

public interface LoggerRequests {
	void log(String requestID, String method, String url, String requestBody, int code, String resp);
}
