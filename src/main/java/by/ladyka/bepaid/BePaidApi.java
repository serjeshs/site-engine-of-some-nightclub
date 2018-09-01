package by.ladyka.bepaid;

import by.ladyka.bepaid.api.PaymentTokenService;
import by.ladyka.bepaid.api.TransactionState;
import by.ladyka.bepaid.dto.GatewayStatus;
import by.ladyka.bepaid.dto.PaymentTokenDto;
import by.ladyka.bepaid.logger.LoggerRequests;
import lombok.*;
import org.apache.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BePaidApi {

	private static BePaidApiConfiguration configuration;
	private static LoggerRequests logger;

	private PaymentTokenService paymentTokenService = new PaymentTokenService();
	private TransactionState transactionState = new TransactionState();

	public static BePaidApi getApi(Long storeId, String key, LoggerRequests l) {
		BePaidApi bePaidApi = new BePaidApi();
		if (configuration == null) {
			configuration = new BePaidApiConfiguration(storeId, key);
			logger = l;
		} else {
			throw new RuntimeException("Configuration is already set!");
		}
		return bePaidApi;
	}

	public static LoggerRequests getLogger() {
		return logger;
	}

	public static BePaidApiConfiguration getConfiguration() {
		return configuration;
	}

	public PaymentTokenDto getOrderToken(PaymentTokenDto dto, String requestId) throws ParseException, IOException, URISyntaxException, NoSuchAlgorithmException {
		return paymentTokenService.getToken(dto, requestId);
	}

	public GatewayStatus getTransactionState(String token, String requestId) throws NoSuchAlgorithmException, IOException, URISyntaxException {
		return transactionState.getStatus(token, requestId);
	}

	@Getter
	@AllArgsConstructor
	public static class BePaidApiConfiguration {
		private Long storeId;
		private String key;
	}
}
