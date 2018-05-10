package by.ladyka.club.utils.converters;

import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.entity.MenuOrderEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MenuOrderConverter {
    public MenuOrderEntity toEntity(MenuOrderDto dto) {
        MenuOrderEntity entity = new MenuOrderEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public MenuOrderDto toDto(MenuOrderEntity entity) {
        MenuOrderDto dto = new MenuOrderDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
