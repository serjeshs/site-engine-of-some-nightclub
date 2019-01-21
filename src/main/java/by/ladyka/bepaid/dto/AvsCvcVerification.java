package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AvsCvcVerification {
	@JsonProperty("cvc_verification")
	private CvcVerification cvcVerification;

	@JsonProperty("avs_verification")
	private AvsVerification avsVerification;

}
