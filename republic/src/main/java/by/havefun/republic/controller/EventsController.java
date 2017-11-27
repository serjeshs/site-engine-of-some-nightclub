package by.havefun.republic.controller;

import by.havefun.GlobalSettings;
import by.havefun.dao.EventDAO;
import by.havefun.entity.AppUser;
import by.havefun.entity.Event;
import by.havefun.republic.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class EventsController {

    private static final Integer MINSK = 2;
    private static final Integer REPUBLIC = 1;
    @Autowired
    EventDAO eventDAO;

    @RequestMapping(value = "api/page/main", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity mainPAge() {
        final List<Event> events = eventDAO.getEventsBetween(
                GlobalSettings.formatter.format(LocalDateTime.now()),
                GlobalSettings.formatter.format(LocalDateTime.now().plusMonths(3L)),
                null,
                MINSK,
                REPUBLIC
        );
        MainPageDTO mainPageDTO = process(events);
        return new ResponseEntity<>(mainPageDTO, HttpStatus.OK);
    }

    private MainPageDTO process(List<Event> events) {
        MainPageDTO mainPageDTO = new MainPageDTO();
        LocalDateTime dateTime = LocalDateTime.now();
        for (Event event : events) {
            final LocalDateTime startEvent = event.getStartEventLDT();
            final long between = ChronoUnit.HOURS.between(dateTime, startEvent.toLocalDate().atStartOfDay());
            if (between < 24) {
                mainPageDTO.today.add(Converter.toEventDTO(event));
            } else if (between < 24 * 2) {
                mainPageDTO.tomorrow.add(Converter.toEventDTO(event));
            } else if (between < 24 * (7 + 7 - LocalDateTime.now().getDayOfWeek().getValue())) {
                mainPageDTO.currentAndNextWeek.add(Converter.toEventDTO(event));
            } else if (startEvent.getMonthValue() == LocalDateTime.now().getMonthValue()) {
                mainPageDTO.currentMonth.add(Converter.toEventDTO(event));
            } else if (startEvent.getMonthValue() == LocalDateTime.now().plusMonths(1L).getMonthValue()) {
                mainPageDTO.nextMonth.add(Converter.toEventDTO(event));
            } else if (startEvent.getMonthValue() == LocalDateTime.now().plusMonths(2L).getMonthValue()) {
                mainPageDTO.nextNextMonth.add(Converter.toEventDTO(event));
            }

        }
        return mainPageDTO;
    }

//    @RequestMapping(value = "api/generate/events")
    public ResponseEntity generateEvents() {
        List<Event> events = new ArrayList<>();
        final AppUser user = new AppUser().setId(1);
        LocalDate localDate = LocalDate.now();
        final Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            localDate = localDate.plusDays(1);
            Timestamp startConcert = Timestamp.valueOf(localDate.atStartOfDay().plusHours(19L));
            Timestamp stopConcert = Timestamp.valueOf(localDate.atStartOfDay().plusHours(22L));
            Timestamp startDisco = Timestamp.valueOf(localDate.atStartOfDay().plusHours(23L));
            Timestamp stopDisco = Timestamp.valueOf(localDate.atStartOfDay().plusDays(1).plusHours(5L));
            switch (localDate.getDayOfWeek().getValue()) {
                case 1:
                    continue;
                case 2: {
                    createEvent(events, user, random, i, startConcert, stopConcert, "Concert2 ");
                }
                break;
                case 3:
                    continue;
                case 4: {
                    continue;
                }
                case 7: {
                    createEvent(events, user, random, i, startDisco, stopDisco, "Disco7 ");
                    break;
                }
                default: {
                    createEvent(events, user, random, i, startConcert, stopConcert, "Concert def");
                    createEvent(events, user, random, i, startDisco, stopDisco, "Disco def");
                }
            }

        }
        return new ResponseEntity<>(events.size(), HttpStatus.OK);
    }

    private void createEvent(List<Event> events, AppUser user, Random random, int i, Timestamp start, Timestamp stop, String titlePrefix) {
        int cost = random.nextInt();
        cost = (cost < 0) ? 0 : cost;
        Event event = new Event(
                cost,
                "COST text example" + cost,
                "Lorem ipsum dolor sit amet, " + i + "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                start,
                stop,
                titlePrefix + "TITLE " + i,
                "RE:PUBLIC",
                "MINSK",
                1, 2, "https://pp.userapi.com/c635102/v635102296/95ff/n39VkTnUcf0.jpg",
                user
        );
        events.add(event);
        eventDAO.save(event);
    }
}
