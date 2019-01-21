package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShopDto {
	private String[] brands;
	@JsonProperty("contact_email")
	private String contactEmail;
	private String name;
	@JsonProperty("contact_phone")
	private String contact_phone;
	private String url;
}
