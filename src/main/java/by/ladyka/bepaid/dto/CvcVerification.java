package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CvcVerification {
	@JsonProperty("result_code")
	private String resultCode;
}
