package by.ladyka.club.service;

import by.ladyka.club.entity.EventTicketPriceEntity;
import by.ladyka.club.entity.EventTicketPriceType;
import by.ladyka.club.repository.EventTicketPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class EventTicketPriceServiceImpl implements EventTicketPriceService {

    @Autowired
    private EventTicketPriceRepository eventTicketPriceRepository;

    @Override
    public Optional<BigDecimal> getLowPriceForEventByPriceType(Long eventId, EventTicketPriceType eventTicketPriceType) {
        List<EventTicketPriceEntity> eventTicketPrices = eventTicketPriceRepository.findAscSortPricesForEventByPriceType(eventId, eventTicketPriceType, PageRequest.of(0, 1));
        if(eventTicketPrices.size() > 0){
            return Optional.of(eventTicketPrices.get(0).getCost());
        }else{
            return Optional.empty();
        }

    }
}
