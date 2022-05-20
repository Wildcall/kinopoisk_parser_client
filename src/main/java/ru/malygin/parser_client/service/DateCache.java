package ru.malygin.parser_client.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DateCache {
    String create();

    String update();

    List<LocalDate> getAvailableDates();
}
