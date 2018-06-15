package by.ladyka.club.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = APP_TABLE_PREFIX + "files")
@EntityListeners(AuditingEntityListener.class)
public class FileEntity  extends AbstractEntity {
    private String filePath;
    private UserEntity user;

}