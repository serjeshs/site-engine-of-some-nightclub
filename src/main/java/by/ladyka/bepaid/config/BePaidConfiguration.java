package by.ladyka.bepaid.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BePaidConfiguration {
	private final String bePaidPaymentDomainPage = "checkout.bepaid.by";
	private final String bePaidPaymentGatewayPortal = "gateway.bepaid.by";
	private final String bePaidPaymentApi = "api.bepaid.by";
	private String bePaidPaymentStoreId;
	private String bePaidPaymentStoreKey;
}