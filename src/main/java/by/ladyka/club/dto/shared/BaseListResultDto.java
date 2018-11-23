package by.ladyka.club.dto.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BaseListResultDto<T> {
	private List<T> items;
	private long total;

	public BaseListResultDto(List<T> items) {
		this.items = items;
		this.total = items.size();
	}
}
