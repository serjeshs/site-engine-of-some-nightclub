package by.ladyka.bepaid.api;

import by.ladyka.bepaid.BePaidApi;
import by.ladyka.bepaid.dto.PaymentTokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * https://docs.bepaid.by/ru/checkout/payment-token
 */
public class PaymentTokenService extends BePaidApiRequests{
	private static final Logger logger = LoggerFactory.getLogger(PaymentTokenService.class);
	private final String url = "https://checkout.bepaid.by/ctp/api/checkouts";

	public PaymentTokenDto getToken(PaymentTokenDto dto, String requestID) throws IOException, ParseException, URISyntaxException, NoSuchAlgorithmException {
		try (CloseableHttpClient httpclient =
				     HttpClientBuilder.create().useSystemProperties()
						     .setSSLSocketFactory(new SSLConnectionSocketFactory(
								     SSLContext.getDefault(),
								     new String[]{"TLSv1.2"},
								     null,
								     SSLConnectionSocketFactory.getDefaultHostnameVerifier()
						     )).build()
		) {
			final HttpPost httppost = new HttpPost(url);

			final String baseAuth = String.format(
					"%d:%s",
					BePaidApi.getConfiguration().getStoreId(),
					BePaidApi.getConfiguration().getKey()
			);

			String encoding = Base64.getEncoder().encodeToString(baseAuth.getBytes());

			httppost.setHeader("Authorization", "Basic " + encoding);
			httppost.setHeader("RequestID", requestID);
			httppost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
			httppost.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
			String jsonRequest = new ObjectMapper().writeValueAsString(dto);
			logger.debug(jsonRequest);
			HttpEntity entity = new StringEntity(jsonRequest, ContentType.APPLICATION_JSON);
			httppost.setEntity(entity);
			System.out.println("Executing request " + httppost.getMethod() + " " + httppost.getURI());

			try (CloseableHttpResponse response = httpclient.execute(httppost)) {
				final String responseEntity = EntityUtils.toString(response.getEntity());
				return new ObjectMapper().readValue(responseEntity, PaymentTokenDto.class);
			}
		}
	}
}