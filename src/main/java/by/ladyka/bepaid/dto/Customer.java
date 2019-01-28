
package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Customer {
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("last_name")
    public String lastName;
    @JsonProperty("address")
    public String address;
    @JsonProperty("city")
    public String city;
    @JsonProperty("country")
    public String country;
    @JsonProperty("state")
    public String state;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("zip")
    public String zip;
    @JsonProperty("email")
    public String email;
    @JsonProperty("birth_date")
    public String birthDate;
}
