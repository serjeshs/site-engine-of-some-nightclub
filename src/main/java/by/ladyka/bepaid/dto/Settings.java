
package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success_url",
    "decline_url",
    "fail_url",
    "cancel_url",
    "notification_url",
    "language",
    "customer_fields"
})
@Data
public class Settings {

    @JsonProperty("success_url")
    public String successUrl;
    @JsonProperty("decline_url")
    public String declineUrl;
    @JsonProperty("fail_url")
    public String failUrl;
    @JsonProperty("cancel_url")
    public String cancelUrl;
    @JsonProperty("notification_url")
    public String notificationUrl;
    @JsonProperty("return_url")
    public String returnUrl;
    @JsonProperty("language")
    public String language;
    @JsonProperty("customer_fields")
    public CustomerFields customerFields;

}
