package by.ladyka.club.service;

import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.repository.EventRepository;
import by.ladyka.club.service.files.StorageService;
import lombok.AllArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final EventRepository eventRepository;
    private final StorageService storageService;

    @Override
    public void fetchCovers() throws IOException {
        List<EventEntity> events = eventRepository.findAll();
        for (EventEntity event : events) {
            String coverUri = event.getCoverUri();
//            Pattern pattern = Pattern.compile("http");
//            Matcher matcher = pattern.matcher(event.getCoverUri());
//            if (matcher.find()){
//            }
            if (coverUri.matches("^http.+")) {
                try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                    HttpGet httpget = new HttpGet(coverUri);
                    CloseableHttpResponse response = httpclient.execute(httpget);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        InputStream content = response.getEntity().getContent();
                        String newName = storageService.store("test", content);
                        event.setCoverUri(newName);
                        eventRepository.save(event);
                    }
                }

            }

        }

    }
}
