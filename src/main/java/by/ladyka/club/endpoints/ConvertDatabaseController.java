package by.ladyka.club.endpoints;

import by.ladyka.club.entity.menu.MenuOrder;
import by.ladyka.club.repository.menu.MenuOrderRepository;
import by.ladyka.club.service.ConvertDatabaseService;
import by.ladyka.club.service.email.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class ConvertDatabaseController {

  private static final Logger logger = LogManager.getLogger(ConvertDatabaseController.class);
  private final ConvertDatabaseService convertDatabaseService;

  @Qualifier("republic")
  @Autowired
  public JavaMailSender emailSender;
  @Autowired
  MenuOrderRepository menuOrderRepository;
  @Autowired
  EmailService emailService;

  @Autowired
  public ConvertDatabaseController(ConvertDatabaseService convertDatabaseService) {
    this.convertDatabaseService = convertDatabaseService;
  }

  @RequestMapping(value = "api/converter", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity f() {
    return new ResponseEntity<>(convertDatabaseService.convertDataBase(), HttpStatus.OK);
  }

  @RequestMapping(value = "api/logger", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity logger() {
    logger.trace("TRACE_TEST_TRACE");
    logger.debug("LOG " + System.currentTimeMillis());
    logger.info("IMAGINATION " + System.currentTimeMillis());
    logger.warn("OLOLO");
    logger.error("О БОЖЕ ТЫ МОЙ!!!");
    logger.debug("Debugging log");
    logger.info("Info log");
    logger.warn("Hey, This is a warning!");
    logger.error("Oops! We have an Error. OK");
    logger.fatal("Damn! Fatal error. Please fix me.");
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @RequestMapping(value = "api/email", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity email(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @RequestMapping(value = "api/email/test", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity emailTest() {
    return email("republictest@ladyka.tk", "RE:TEST", LocalDateTime.now().toString());
  }

  @RequestMapping(value = "api/email/test/1", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  String emailTest1() {
    final Optional<MenuOrder> byId = menuOrderRepository.findById(9L);
    final MenuOrder menuOrder = byId.get();
    emailService.sendOrderToOwner(menuOrder);
    return "{ \"ololo\":42}";
  }
}
