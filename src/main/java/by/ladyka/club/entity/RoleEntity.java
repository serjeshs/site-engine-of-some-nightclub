package by.ladyka.club.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = APP_TABLE_PREFIX + "system_role")
@EntityListeners(AuditingEntityListener.class)
public class RoleEntity extends AbstractEntity implements GrantedAuthority {

    private static final long serialVersionUID = 3L;
    private String authority;
}