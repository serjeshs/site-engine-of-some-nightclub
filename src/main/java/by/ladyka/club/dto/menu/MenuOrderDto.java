package by.ladyka.club.dto.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MenuOrderDto {
    private Map<String, String> food;
    private String name;
    private String email;
    private String phone;
    private Integer event;
    private String arrivalTime;
    private Integer people;
    private String bcCode;
    private String description;
}
