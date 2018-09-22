package by.ladyka.club.service;

import by.ladyka.club.dto.UserDataDto;
import by.ladyka.club.entity.UserDataStatisticEntity;
import by.ladyka.club.repository.UserDataStatisticRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

	@Autowired
	UserDataStatisticRepository userDataStatisticRepository;

	public boolean save(UserDataDto dto, String localAddr, String remoteAddr, String protocol) {
		UserDataStatisticEntity entity = new UserDataStatisticEntity();
		BeanUtils.copyProperties(dto, entity);
		entity.setLocalAddr(localAddr);
		entity.setRemoteAddr(remoteAddr);
		entity.setProtocol(protocol);
		userDataStatisticRepository.save(entity);
		return true;
	}
}
