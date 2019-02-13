package by.ladyka.club.service;

import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.repository.EventRepository;
import by.ladyka.club.service.files.StorageService;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PictureServiceImplTest {

    @MockBean
    private EventRepository eventRepository;
    @MockBean
    private StorageService storageService;

    private PictureService pictureService;
    private EventEntity eventEntity;

    @Before
    public void setUp() throws Exception {

        pictureService = new PictureServiceImpl(eventRepository, storageService);
        eventEntity = new EventEntity();
        eventEntity.setCoverUri("https://pp.userapi.com/c841137/v841137810/7580a/l2RQOIh2G9E.jpg");
//        eventEntity.setCoverUri("userapi.com/c841137/v841137810/7580a/l2RQOIh2G9E.jpg");
        when(eventRepository.findAll()).thenReturn(Collections.singletonList(eventEntity));
//        when(storageService.store())

    }

    @Test
    public void fetchCovers() {
        try {
            pictureService.fetchCovers();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}