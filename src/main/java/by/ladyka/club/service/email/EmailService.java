package by.ladyka.club.service.email;

import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.entity.menu.MenuOrder;

import javax.mail.MessagingException;

public interface EmailService {
  void sendOrderToOwner(MenuOrder order);
}
