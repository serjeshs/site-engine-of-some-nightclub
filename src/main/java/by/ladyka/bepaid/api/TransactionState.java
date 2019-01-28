package by.ladyka.bepaid.api;

import by.ladyka.bepaid.dto.GatewayStatus;
import by.ladyka.bepaid.dto.PaymentTokenDto;
import by.ladyka.bepaid.dto.PaymentTokenResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

/**
 * https://docs.bepaid.by/ru/checkout/query
 */
public class TransactionState extends BePaidApiRequests {

	private static final String url = "https://checkout.bepaid.by/ctp/api/checkouts/%s";

	public GatewayStatus getStatus(String paymentToken, String requestId) throws NoSuchAlgorithmException, IOException, URISyntaxException {
		final PaymentTokenResultDto dto = getTransactionState(paymentToken, requestId);
		return GatewayStatus.valueOf(dto.getCheckout().getStatus());
	}

	private PaymentTokenResultDto getTransactionState(String paymentToken, String requestId) throws IOException, NoSuchAlgorithmException {
		String response = executeGet(String.format(url, paymentToken), requestId);
		return new ObjectMapper().readValue(response, PaymentTokenResultDto.class);
	}

}
