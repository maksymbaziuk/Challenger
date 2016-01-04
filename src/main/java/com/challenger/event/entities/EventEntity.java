package com.challenger.event.entities;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;

/**
 * Created by Maksym_Baziuk on 22.11.2015.
 */
@Entity
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
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
