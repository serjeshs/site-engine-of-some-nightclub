
package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PaymentTokenResultDto {
    private CheckoutBePaidResult checkout;
}
