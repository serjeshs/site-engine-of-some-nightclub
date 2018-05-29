package by.ladyka.club.service.email;


import by.ladyka.club.entity.menu.MenuItemPricesHasOrders;
import by.ladyka.club.entity.menu.MenuOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class EmailServiceImpl implements EmailService {

  @Qualifier("republic")
  @Autowired
  private JavaMailSender emailSender;

  @Autowired
  private TemplateEngine templateEngine;

  @Override
  public void sendOrderToOwner(MenuOrder order) {
    String subject = "RE:PUBLIC Заказ № " + order.getId();
    try {
      MimeMessage message = emailSender.createMimeMessage();
      message.setSubject(subject);
      MimeMessageHelper helper;
      helper = new MimeMessageHelper(message, true, "utf-8");
      helper.setTo(order.getEmail());
      helper.setCc("orders@republic-club.by");
      helper.setText(buildOrderText(order), true);
      new Thread(() -> emailSender.send(message)).start();
    } catch (MessagingException ex) {
      ex.printStackTrace();
    }

  }

  private String buildOrderText(MenuOrder order) {
    final Locale.Builder builder = new Locale.Builder();
    final Context ctx = new Context(builder.build());
    ctx.setVariable("order", order);
    final List<MenuItemPricesHasOrders> itemPricesHasOrders = order.getItemPricesHasOrders();
    ctx.setVariable("food", itemPricesHasOrders);
    ctx.setVariable("currentDate", new Date());
    String imageResourceName = "http://new3.republic-club.by/assets/img/logo2.jpg";
    ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML

    return templateEngine.process("email/orderCustomer.html", ctx);
  }
}
