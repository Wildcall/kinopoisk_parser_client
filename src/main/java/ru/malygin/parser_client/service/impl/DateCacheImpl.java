package ru.malygin.parser_client.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.malygin.parser_client.service.DateCache;
import ru.malygin.parser_client.service.FilmService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DateCacheImpl implements DateCache {

    private final FilmService filmService;
    private final List<LocalDate> cache = new ArrayList<>();

    @Override
    public String create() {
        long time = System.currentTimeMillis();
        cacheLoad();
        return "Total date: " + cache.size() +
                " | Created in: " + (System.currentTimeMillis() - time) + " ms.";
    }

    @Override
    public String update() {
        long time = System.currentTimeMillis();
        cacheLoad();
        return "Total date: " + cache.size() +
                " | Updated in: " + (System.currentTimeMillis() - time) + " ms.";
    }

    @Override
    public List<LocalDate> getAvailableDates() {
        return new ArrayList<>(cache);
    }

    private void cacheLoad() {
        cache.clear();
        List<LocalDate> datesFromDB = filmService.getListOfAvailableDate();
        cache.addAll(datesFromDB);
    }
}
