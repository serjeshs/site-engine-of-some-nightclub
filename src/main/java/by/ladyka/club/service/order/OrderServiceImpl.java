package by.ladyka.club.service.order;

import by.ladyka.bepaid.dto.GatewayStatus;
import by.ladyka.club.repository.menu.MenuOrderRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private MenuOrderRepository menuOrderRepository;
	private OrderTicketsService orderTicketsService;

	@Override
	public void success(final String uuid, final String status, final String uid, final String token) {
		final GatewayStatus gatewayStatus = GatewayStatus.valueOf(status);
		if (orderTicketsService.updateStatus(uuid, gatewayStatus, uid, token)) {
			//All OK
		} else {
			menuOrderRepository.findByUuid(uuid).ifPresent(menuOrder -> {
				if (StringUtils.equals(menuOrder.getToken(), token)) {
					menuOrder.setPayStatus(gatewayStatus);
					menuOrder.setUid(uid);
					menuOrderRepository.save(menuOrder);
				} else {
					throw new RuntimeException("Token is invalid");
				}
			});
		}
	}
}
