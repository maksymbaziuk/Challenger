package com.challenger.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by Maksym_Baziuk on 24.11.2015.
 */
@Component
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        SimpleModule module = new SimpleModule("JSONModule", new Version(2, 0, 0, null, null, null));
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        registerModule(module);
    }
}
