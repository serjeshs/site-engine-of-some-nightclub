
package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "currency",
    "amount",
    "description"
})
@Data
public class OrderDto {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("amount")
    private long amount;
    @JsonProperty("description")
    private String description;
    @JsonProperty("tracking_id")
    private Long trackingId;
    @JsonProperty("additional_data")
    private AdditionalData additionalData;
    @JsonProperty("expired_at")
    private Object expiredAt;



}
