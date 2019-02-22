package by.ladyka.club.endpoints;

import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.service.ConverterEventService;
import by.ladyka.club.service.ConverterEventServiceImpl;
import by.ladyka.club.service.EventsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(EventsHtmlController.class)
@AutoConfigureMockMvc(secure = false)
public class EventsHtmlControllerTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ConverterEventService converterEventService() {
            return new ConverterEventServiceImpl();
        }
    }

    @MockBean
    private EventsService eventsService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void metaPropertiesForVkAndTelegramExists() throws Exception {

        EventEntity event = new EventEntity();
        event.setId(3L);
        event.setName("Event #1");
        event.setDescription("Text about event #1");
        event.setCoverUri("image.jpg");

        given(eventsService.getEventById(3L)).willReturn(Optional.of(event));

        String responseHtml = mvc.perform(get("/event/3")).andReturn().getResponse().getContentAsString();

        Document documentResponseHtml = Jsoup.parse(responseHtml);

        assertEquals(
                event.getName(),
                documentResponseHtml
                        .select("meta[property=og:title]")
                        .attr("content"));
        assertEquals(
                event.getDescription(),
                documentResponseHtml
                        .select("meta[property=og:description]")
                        .attr("content"));
        assertEquals(
                "http://localhost:80/files/" + event.getCoverUri(),
                documentResponseHtml
                        .select("meta[property=og:image]")
                        .attr("content"));

    }
}
