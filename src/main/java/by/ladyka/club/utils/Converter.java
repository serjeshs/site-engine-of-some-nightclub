//package by.ladyka.club.utils;
//
//import by.ladyka.club.dto.EventDTO;
//import by.ladyka.club.entity.Event;
//import by.ladyka.club.entity.old.ModxSiteContent;
//import by.ladyka.club.entity.old.ModxSiteTmplVarContentValues;
//import by.ladyka.club.entity.old.ModxSiteTmplVars;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//public class Converter {
//    public static EventDTO toEventDto(ModxSiteContent modxSiteContent) {
//        EventDTO eventDTO = new EventDTO();
//        eventDTO.setId(modxSiteContent.getId());
//        eventDTO.setName(modxSiteContent.getLongtitle());
//        eventDTO.setDescription(modxSiteContent.getContent());
//        eventDTO.setCost(new BigDecimal(0));
//        eventDTO.setCostText(getCostText(modxSiteContent.getContentValues()));
//        return eventDTO;
//    }
//
//    private static String getCostText(List<ModxSiteTmplVarContentValues> contentValues) {
//        final Long priceId = ModxSiteTmplVars.price.getId();
//        return contentValues.stream()
//                .filter(values -> Objects.equals(priceId, values.getTmplvarid()))
//                .map(ModxSiteTmplVarContentValues::getValue)
//                .collect(Collectors.joining());
//    }
//
//}
