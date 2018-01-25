package by.ladyka.club.dto;

import by.ladyka.club.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUser {
    private final UserEntity userEntity;

    public AppUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
