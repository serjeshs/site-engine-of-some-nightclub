package by.ladyka.club.service;

import by.ladyka.club.dto.NewsDto;
import by.ladyka.club.entity.NewsEntity;
import by.ladyka.club.entity.UserEntity;
import by.ladyka.club.repository.NewsEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsEntityRepository newsEntityRepository;

	@Autowired
	private UserService userService;

	@Override
	public List<NewsDto> summary() {
		List<NewsEntity> newsEntities = newsEntityRepository.findAllByVisibleIsTrueOrderByCreatedDateDesc();
		return newsEntities.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public NewsDto byId(Long id) {
		return convertToDto(newsEntityRepository.getOne(id));
	}

	@Override
	public NewsDto save(NewsDto dto, String owner) {
		UserEntity userEntity = userService.getUserEntity(owner);
		NewsEntity entity = (dto.getId() == null) ? new NewsEntity() : newsEntityRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("NewsEntity not found!"));
		entity.setOwner(userEntity);
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setDescriptionPreview(dto.getDescriptionPreview());
		entity.setAlias("NO" + System.currentTimeMillis());
		entity.setImage(dto.getImage());
		return convertToDto(newsEntityRepository.save(entity));
	}

	private NewsDto convertToDto(NewsEntity entity) {
		if (entity == null) {
			return new NewsDto();
		}
		NewsDto dto = new NewsDto();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		dto.setDescriptionPreview(entity.getDescriptionPreview());
		dto.setTitle(entity.getTitle());
		dto.setOwner(entity.getOwner().getPublishName());
		dto.setImage(entity.getImage());
		dto.setCreateDate(entity.getCreatedDate());
		return dto;
	}
}
