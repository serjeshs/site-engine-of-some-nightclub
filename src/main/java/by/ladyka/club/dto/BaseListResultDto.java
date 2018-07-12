package by.ladyka.club.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BaseListResultDto<T> {
	private List<T> items;
	private long total;
}
