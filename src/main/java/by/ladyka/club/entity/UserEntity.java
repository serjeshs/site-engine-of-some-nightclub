package by.ladyka.club.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends AbstractEntity implements UserDetails {

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	@Type(type = "boolean")
	private Boolean enabled;

	@Getter
	@Setter
	private String name;


	@Getter
	@Setter
	private String surname;

	@Getter
	@Setter
	private String email;


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


	@Getter
	@Setter
	@OneToMany(mappedBy = "user")
	private List<AuthorityEntity> authorities;

	public static UserEntity unconfirmUser() {
		UserEntity userEntity = new UserEntity();
		userEntity.username = "username" + System.currentTimeMillis() + "@republic-club.by";
		userEntity.password = "password" + System.currentTimeMillis();
		userEntity.name = "name";
		userEntity.surname = "surname";
		userEntity.fatherName = "fatherName";
		userEntity.birthday = OffsetDateTime.now();
		userEntity.accountNonExpired = true;
		userEntity.accountNonLocked = true;
		userEntity.credentialsNonExpired = false;
		userEntity.enabled = true;
		userEntity.authorities = Collections.emptyList();
		return userEntity;
	}

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

	public String getPublishName() {
		return String.format("%s %s", getSurname(), getName());
	}

}