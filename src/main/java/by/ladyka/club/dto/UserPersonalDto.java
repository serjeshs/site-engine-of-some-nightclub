package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserPersonalDto {
	private String username;
	private String name;
	private String surname;
	private String fatherName;
	private String email;
	private String phone;
	private OffsetDateTime birthday;
	private List<String> authorities = new ArrayList<>();
}
