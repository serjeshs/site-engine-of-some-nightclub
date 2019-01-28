package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentDto {
	private String redirect_url;
	private String billing_descriptor;
	private String uid;
	@JsonProperty("gateway_id")
	private String gatewayId;
	private String rrn;
	private Object gateway; //TODO ????
	private String status;
	private String type;
	@JsonProperty("bank_code")
	private String bankCode;
	private String currency;
	private String message;
	private String amount;
	@JsonProperty("avs_cvc_verification")
	private AvsCvcVerification avsCvcVerification;
	@JsonProperty("updated_at")
	private String updatedAt;
	@JsonProperty("auth_code")
	private String authCode;
	@JsonProperty("ref_id")
	private String refIId;

}
