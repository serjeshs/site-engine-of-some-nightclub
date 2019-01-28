package by.ladyka.bepaid.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BePaidConfiguration {
	public final String bePaidPaymentDomainPage = "checkout.bepaid.by";
	public final String bePaidPaymentGatewayPortal = "gateway.bepaid.by";
	public final String bePaidPaymentApi = "api.bepaid.by";
	public static final double BE_PAID_VERSION = 2.1D;
}