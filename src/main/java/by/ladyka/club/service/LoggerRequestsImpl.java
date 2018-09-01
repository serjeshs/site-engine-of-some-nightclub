package by.ladyka.club.service;

import by.ladyka.bepaid.logger.LoggerRequests;
import by.ladyka.club.entity.BePaidRequest;
import by.ladyka.club.repository.BePaidRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerRequestsImpl implements LoggerRequests {

	@Autowired
	BePaidRequestsRepository bePaidRequestsRepository;

	@Override
	public void log(String requestId, String method, String url, String requestBody, int code, String responseBody) {
		BePaidRequest log = new BePaidRequest();
		log.setRequestId(requestId);
		log.setMethod(method);
		log.setUrl(url);
		log.setRequestBody(requestBody);
		log.setCode(code);
		log.setResponseBody(responseBody);
		bePaidRequestsRepository.save(log);
	}
}
