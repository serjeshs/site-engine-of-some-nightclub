package by.ladyka.club.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;


@Entity
@Table(name = APP_TABLE_PREFIX + "system_user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends AbstractEntity implements UserDetails {

    @Column(name = "email")
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;


    @Getter
    @Setter
    private String name;


    @Getter
    @Setter
    private String surname;


    @Getter
    @Setter
    @Column(name = "father_name")
    private String fatherName;


    @Getter
    @Setter
    private OffsetDateTime birthday;


    @Setter
    @Type(type = "boolean")
    private Boolean accountNonExpired;

    @Setter
    @Type(type = "boolean")
    private Boolean accountNonLocked;
    @Setter
    @Type(type = "boolean")
    private Boolean credentialsNonExpired;
    @Setter
    @Type(type = "boolean")
    private Boolean enabled;

    @Getter
    @Setter
    @ManyToMany
    private List<RoleEntity> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}