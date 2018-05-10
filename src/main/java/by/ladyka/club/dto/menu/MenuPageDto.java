package by.ladyka.club.dto.menu;

import by.ladyka.club.dto.EventDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MenuPageDto {
    private List<MenuCategoryDto> categories;
    private List<EventDTO> events;
}
