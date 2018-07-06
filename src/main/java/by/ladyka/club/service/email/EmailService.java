package by.ladyka.club.service.email;

import by.ladyka.club.dto.FeedBackDto;
import by.ladyka.club.entity.FeedBackEntity;
import by.ladyka.club.entity.menu.MenuOrder;

public interface EmailService {
	void sendOrderToOwner(MenuOrder order);

	void sendFeedBack(FeedBackEntity feedBack);
}
