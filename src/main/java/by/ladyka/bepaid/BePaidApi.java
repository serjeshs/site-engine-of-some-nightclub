package by.ladyka.bepaid;

import by.ladyka.bepaid.api.PaymentTokenService;
import by.ladyka.bepaid.dto.PaymentTokenDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BePaidApi {

	public static BePaidApiConfiguration configuration;

	public PaymentTokenService paymentTokenService = new PaymentTokenService();

	public static BePaidApi getApi(Long storeId, String key) {
		if (configuration == null) {
			configuration = new BePaidApiConfiguration(storeId, key);
		} else {
			throw new RuntimeException("Configuration is already set!");
		}
		return new BePaidApi();
	}

	public PaymentTokenDto getOrderToken(PaymentTokenDto dto, String requestId) throws ParseException, IOException, URISyntaxException, NoSuchAlgorithmException {
		return paymentTokenService.getToken(dto, requestId);
	}

	@Getter
	@AllArgsConstructor
	public static class BePaidApiConfiguration {
		private Long storeId;
		private String key;
	}
}
