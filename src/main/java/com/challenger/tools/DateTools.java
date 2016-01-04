package com.challenger.tools;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Maksym_Baziuk on 22.11.2015.
 */
@Component
public class DateTools {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final Pattern datePattern = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

    public boolean validateDate(String date){
        return StringUtils.isNotBlank(date) && datePattern.matcher(date).matches();
    }

    public Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    public LocalDate parseLocalDate(String date){
        return LocalDate.parse(date);
    }
}
