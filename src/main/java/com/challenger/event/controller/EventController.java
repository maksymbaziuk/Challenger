package com.challenger.event.controller;

import com.challenger.event.bean.Event;
import com.challenger.event.service.EventService;
import com.challenger.tools.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Maksym_Baziuk on 22.11.2015.
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private DateTools dateTools;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    @ResponseBody
    public Object createEvent(@RequestParam String date, @RequestParam long challengeId) throws ParseException {
        Object result = null;
        if (!dateTools.validateDate(date) || challengeId == 0){
            result = "Invalid request!";
        } else {
            Event event = new Event();
            event.setEventDate(LocalDate.parse(date));
            result = eventService.createEvent(event, challengeId);
        }
        return result;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Object findAll(){
        List<Event> eventList = eventService.findAllEvents();
        return eventList;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Object findEvent(@RequestParam long id){
        return eventService.findEventById(id);
    }

}
