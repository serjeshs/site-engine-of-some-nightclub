package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUser {
	private final UserDto userDto;

	public AppUser(UserDto userDto) {
		this.userDto = userDto;
	}
}
