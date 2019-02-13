package by.ladyka.club.service;

import by.ladyka.club.config.CustomSettings;
import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.repository.EventRepository;
import by.ladyka.club.service.files.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@DataJpaTest
@Transactional
@RunWith(SpringRunner.class)
//@ComponentScan({"by.ladyka.club.service.files", "by.ladyka.club.config"})
@ComponentScan({"by.ladyka.club.service.files"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PictureServiceImplTest {

//    @MockBean
    @Autowired
    private EventRepository eventRepository;

//    @MockBean
    @Autowired
    private StorageService storageService;

    private PictureService pictureService;
    private EventEntity eventEntity;

    @Before
    public void setUp() throws Exception {

        pictureService = new PictureServiceImpl(eventRepository, storageService);
        eventEntity = new EventEntity();
        eventEntity.setCoverUri("https://pp.userapi.com/c841137/v841137810/7580a/l2RQOIh2G9E.jpg");
//        eventEntity.setCoverUri("userapi.com/c841137/v841137810/7580a/l2RQOIh2G9E.jpg");
        eventRepository.save(eventEntity);
//        when(eventRepository.findAll()).thenReturn(Collections.singletonList(eventEntity));
//        when(storageService.store()).thenReturn()

    }

    @Test
    public void fetchCovers() {
        try {
            pictureService.fetchCovers();
            List<EventEntity> eventList = eventRepository.findAll();
            for (EventEntity event : eventList
                 ) {
                System.out.println(event.getCoverUri());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}