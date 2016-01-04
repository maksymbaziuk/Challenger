package com.challenger.event.bean;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Maksym_Baziuk on 22.11.2015.
 */
public class Event {

    private long id;
    private LocalDate eventDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
}
