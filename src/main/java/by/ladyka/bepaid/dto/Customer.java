
package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address",
    "country",
    "city",
    "email"
})
@Data
public class Customer {

    @JsonProperty("address")
    public String address;
    @JsonProperty("country")
    public String country;
    @JsonProperty("city")
    public String city;
    @JsonProperty("email")
    public String email;
    @JsonProperty("last_name")
    public String lastName;
    @JsonProperty("phone")
    public String phone;

}
