package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private Long id;
	private String username;
	private String email;
	private String name;
	private String role;
	private String password;
}
