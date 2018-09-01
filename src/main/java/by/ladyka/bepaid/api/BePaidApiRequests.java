package by.ladyka.bepaid.api;

import by.ladyka.bepaid.BePaidApi;
import by.ladyka.bepaid.logger.LoggerRequests;
import lombok.Getter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public abstract class BePaidApiRequests {

	private LoggerRequests logger = BePaidApi.getLogger();

	protected String executePost(final String url, final String jsonRequest, final String requestID) throws IOException, ParseException, URISyntaxException, NoSuchAlgorithmException {
		HttpPost request = new HttpPost(url);
		HttpEntity entity = new StringEntity(jsonRequest, ContentType.APPLICATION_JSON);
		request.setEntity(entity);
		return execute(request, requestID);
	}

	protected String executeGet(String url, final String requestID) throws IOException, ParseException, URISyntaxException, NoSuchAlgorithmException {
		return execute(new HttpGet(url), requestID);
	}

	private String execute(HttpRequestBase request, final String requestID) throws IOException, ParseException, URISyntaxException, NoSuchAlgorithmException {
		String resp;
		int code;
		String requestBody = "no body";
		final String baseAuth = String.format(
				"%d:%s",
				BePaidApi.getConfiguration().getStoreId(),
				BePaidApi.getConfiguration().getKey()
		);
		String encoding = Base64.getEncoder().encodeToString(baseAuth.getBytes());
		request.setHeader("Authorization", "Basic " + encoding);
		request.setHeader("RequestID", requestID);
		request.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
		request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
		ResponseData responseData = new ResponseData(request).invoke();
		resp = responseData.getResp();
		code = responseData.getCode();

		if (logger != null) {
			logger.log(requestID, request.getMethod(), request.getURI().toASCIIString(), requestBody, code, resp);
		}
		return resp;

	}

	private class ResponseData {
		private HttpRequestBase request;
		@Getter
		private String resp;
		@Getter
		private int code;

		ResponseData(HttpRequestBase request) {
			this.request = request;
		}

		ResponseData invoke() throws IOException, NoSuchAlgorithmException {
			try (CloseableHttpClient httpclient =
					     HttpClientBuilder.create().useSystemProperties()
							     .setSSLSocketFactory(new SSLConnectionSocketFactory(
									     SSLContext.getDefault(),
									     new String[]{"TLSv1.2"},
									     null,
									     SSLConnectionSocketFactory.getDefaultHostnameVerifier()
							     )).build()
			) {
				try (CloseableHttpResponse response = httpclient.execute(request)) {
					resp = EntityUtils.toString(response.getEntity());
					code = response.getStatusLine().getStatusCode();
				}
			}
			return this;
		}
	}
}
