package by.ladyka.club.service;

import by.ladyka.club.dto.UserDto;
import by.ladyka.club.entity.UserEntity;
import by.ladyka.club.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserEntityRepository userEntityRepository;

	@Override
	public UserDto getUser(String username) {
		final UserEntity user = userEntityRepository.findByUsername(username);
		UserDto userDto = new UserDto();
		userDto.setUsername(user.getUsername());
		return userDto;
	}
}
