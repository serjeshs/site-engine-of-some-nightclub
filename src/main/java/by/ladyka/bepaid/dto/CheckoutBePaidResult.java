package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckoutBePaidResult extends Checkout {
	private ShopDto shop;
	private String expired;
	@JsonProperty("shop_id")
	private Long shopId;
	private String status;
	private String finished;
	private Customer customer;
	private String message;
	private Object card_info; //TODO ???
	@JsonProperty("gateway_response")
	private GatewayResponseDto gatewayResponse;
	private String payment_method;
}
