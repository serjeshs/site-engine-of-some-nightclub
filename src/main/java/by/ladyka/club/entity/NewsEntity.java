package by.ladyka.club.entity;

import by.ladyka.club.entity.menu.MenuCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;


@Entity
@Getter
@Setter
@Table(name = APP_TABLE_PREFIX + "news")
@EntityListeners(AuditingEntityListener.class)
public class NewsEntity extends AbstractEntity {

    private String title;
    @Lob
    private String descriptionPreview;
    @Lob
    private String description;
    private String alias;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId")
    private UserEntity owner;
}
