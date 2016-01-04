package com.challenger.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Maksym_Baziuk on 24.11.2015.
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(new JSONDateFormat(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), localDate.getDayOfWeek().toString()));
    }

    public static class JSONDateFormat {

        public JSONDateFormat(int year, int month, int dayOfMonth, String dayOfWeek) {
            this.year = year;
            this.month = month;
            this.dayOfMonth = dayOfMonth;
            this.dayOfWeek = dayOfWeek;
        }

        private int year;
        private int month;
        private int dayOfMonth;
        private String dayOfWeek;

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDayOfMonth() {
            return dayOfMonth;
        }

        public String getDayOfWeek() {
            return dayOfWeek;
        }
    }
}
