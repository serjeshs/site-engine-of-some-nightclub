package by.ladyka.club.service;

import by.ladyka.club.entity.EventTicketPriceType;

import java.math.BigDecimal;
import java.util.Optional;

public interface EventTicketPriceService {
    Optional<BigDecimal> getLowPriceForEventByPriceType(Long eventId, EventTicketPriceType eventTicketPriceType);
}
