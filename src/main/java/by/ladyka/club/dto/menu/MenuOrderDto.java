package by.ladyka.club.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
