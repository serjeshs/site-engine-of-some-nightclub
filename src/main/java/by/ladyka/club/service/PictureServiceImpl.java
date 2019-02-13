package by.ladyka.club.service;

import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.repository.EventRepository;
import by.ladyka.club.service.files.StorageService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final EventRepository eventRepository;
    private final StorageService storageService;

    private static final Logger logger = LogManager.getLogger(PictureServiceImpl.class);

    @Override
    public void fetchCovers() throws IOException {
        List<EventEntity> events = eventRepository.findAll();
        for (EventEntity event : events) {
            String coverUri = event.getCoverUri();
            if (coverUri.matches("^http.+")) {
                try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                    HttpGet httpget = new HttpGet(coverUri);
                    CloseableHttpResponse response = httpclient.execute(httpget);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        InputStream content = response.getEntity().getContent();
                        String newName = storageService.store(FilenameUtils.getName(coverUri), content);
                        event.setCoverUri(newName);
                        eventRepository.save(event);
                        response.close();
                    } else {
                        logger.error("remote server returned an error");
                    }
                }

            }

        }

    }
}
