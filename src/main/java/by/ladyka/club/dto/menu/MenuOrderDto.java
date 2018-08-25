package by.ladyka.club.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuOrderDto {
	private Long id;
	private Map<Long, Integer> food;
	private String name;
	private String email;
	private String phone;
	private Long event;
	private String eventName;
	private String arrivalTime;
	private Integer people;
	private String bcCode;
	private String description;
	private Integer tableNumber;
	private String uuid = UUID.randomUUID().toString();
	private String payStatus;
}
