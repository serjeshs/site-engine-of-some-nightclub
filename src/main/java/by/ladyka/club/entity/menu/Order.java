//package by.ladyka.club.entity.menu;
//
//import by.ladyka.club.entity.Event;
//import lombok.Getter;
//import lombok.Setter;
//import by.ladyka.club.entity.AbstractEntity;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.Entity;
//import javax.persistence.EntityListeners;
//import javax.persistence.Table;
//
//import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;
//
//@Entity
//@Getter
//@Setter
//@Table(name = APP_TABLE_PREFIX + "order")
//@EntityListeners(AuditingEntityListener.class)
//public class Order extends AbstractEntity {
//    private String name;
//    private String contactInfo;
//    private Event event;
//    private String notes;
//}