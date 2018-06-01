package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MainPageDTO {
	private List<EventDTO> today = new ArrayList<>();
	private List<EventDTO> tomorrow = new ArrayList<>();
	private List<EventDTO> currentAndNextWeek = new ArrayList<>();
	private List<EventDTO> currentMonth = new ArrayList<>();
	private List<EventDTO> nextMonth = new ArrayList<>();
	private List<EventDTO> nextNextMonth = new ArrayList<>();
	private List<EventRelevantDTO> relevant = new ArrayList<>();
	private List<EventGalleryDTO> gallery = new ArrayList<>();
}
