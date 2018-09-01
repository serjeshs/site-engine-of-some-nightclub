package by.ladyka.club.service.order;

import by.ladyka.bepaid.dto.GatewayStatus;
import by.ladyka.club.repository.menu.MenuOrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private MenuOrderRepository menuOrderRepository;

	@Override
	public void success(final String uuid, final String status, final String uid, final String token) {
		menuOrderRepository.findByUuid(uuid).ifPresent(menuOrder -> {
			if (StringUtils.equals(menuOrder.getToken(), token)) {
				menuOrder.setPayStatus(GatewayStatus.valueOf(status));
				menuOrder.setUid(uid);
				menuOrderRepository.save(menuOrder);
			} else {
				throw new RuntimeException("Token is invalid");
			}
		});
	}
}
