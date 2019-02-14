package by.ladyka.club.service;

import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.repository.EventRepository;
import by.ladyka.club.service.files.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class PictureServiceImplTest {

    private static final String TEST_URL = "https://pp.userapi.com/c841137/v841137810/7580a/l2RQOIh2G9E.jpg";
    private static final String FILE_NAME_WITHOUT_EXT = "l2RQOIh2G9E";

    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private StorageService storageService;

    private PictureService pictureService;
    private EventEntity eventEntity;

    @Before
    public void setUp() throws Exception {

        pictureService = new PictureServiceImpl(eventRepository, storageService);
        eventEntity = new EventEntity();
        eventEntity.setCoverUri(TEST_URL);
        eventRepository.save(eventEntity);
        when(eventRepository.findAll()).thenReturn(Collections.singletonList(eventEntity));

    }

    @Test
    public void fetchCovers() throws IOException {
        pictureService.fetchCovers();
        List<EventEntity> eventList = eventRepository.findAll();
        for (EventEntity event : eventList
        ) {
            assertTrue(FILE_NAME_WITHOUT_EXT.equals(event.getCoverUri().substring(11)));
        }

    }
}