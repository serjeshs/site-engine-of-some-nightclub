package by.ladyka.club.service.email;

import by.ladyka.club.entity.menu.MenuOrder;

public interface EmailService {
	void sendOrderToOwner(MenuOrder order);
}
