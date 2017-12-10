package by.havefun.republic.controller;

import by.havefun.republic.dto.EventDTO;
import by.havefun.republic.dto.EventGalleryDTO;
import by.havefun.republic.dto.EventRelevantDTO;

import java.util.ArrayList;
import java.util.List;

public class MainPageDTO {
    List<EventDTO> today = new ArrayList<>();
    List<EventDTO> tomorrow = new ArrayList<>();
    List<EventDTO> currentAndNextWeek = new ArrayList<>();
    List<EventDTO> currentMonth = new ArrayList<>();
    List<EventDTO> nextMonth = new ArrayList<>();
    List<EventDTO> nextNextMonth = new ArrayList<>();
    List<EventRelevantDTO> relevant = new ArrayList<>();
    List<EventGalleryDTO> gallery = new ArrayList<>();

    public List<EventDTO> getToday() {
        return today;
    }

    public void setToday(List<EventDTO> today) {
        this.today = today;
    }

    public List<EventDTO> getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(List<EventDTO> tomorrow) {
        this.tomorrow = tomorrow;
    }

    public List<EventDTO> getCurrentAndNextWeek() {
        return currentAndNextWeek;
    }

    public void setCurrentAndNextWeek(List<EventDTO> currentAndNextWeek) {
        this.currentAndNextWeek = currentAndNextWeek;
    }

    public List<EventDTO> getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(List<EventDTO> currentMonth) {
        this.currentMonth = currentMonth;
    }

    public List<EventDTO> getNextMonth() {
        return nextMonth;
    }

    public void setNextMonth(List<EventDTO> nextMonth) {
        this.nextMonth = nextMonth;
    }

    public List<EventDTO> getNextNextMonth() {
        return nextNextMonth;
    }

    public void setNextNextMonth(List<EventDTO> nextNextMonth) {
        this.nextNextMonth = nextNextMonth;
    }

    public List<EventRelevantDTO> getRelevant() {
        return relevant;
    }

    public void setRelevant(List<EventRelevantDTO> relevant) {
        this.relevant = relevant;
    }

    public List<EventGalleryDTO> getGallery() {
        return gallery;
    }

    public void setGallery(List<EventGalleryDTO> gallery) {
        this.gallery = gallery;
    }
}
