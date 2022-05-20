package ru.malygin.parser_client.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.malygin.parser_client.model.dto.impl.FilmDto;
import ru.malygin.parser_client.service.DateCache;
import ru.malygin.parser_client.service.FilmCache;
import ru.malygin.parser_client.service.FilmService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class FilmDtoCacheImpl implements FilmCache {

    private final FilmService filmService;
    private final DateCache dateCache;
    private final Map<LocalDate, List<FilmDto>> cache = new ConcurrentHashMap<>();

    @Override
    public String create() {
        long time = System.currentTimeMillis();
        cacheLoad();
        return "Created in: " + (System.currentTimeMillis() - time) + " ms.";
    }

    @Override
    public String update() {
        long time = System.currentTimeMillis();
        cacheLoad();
        return "Update in: " + (System.currentTimeMillis() - time) + " ms.";
    }

    @Override
    public List<FilmDto> getFilmsOnDate(LocalDate date) {
        return cache.get(date);
    }

    private void cacheLoad() {
        cache.clear();
        dateCache
                .getAvailableDates()
                .forEach(date -> {
                    List<FilmDto> datesFromDB = filmService.findFilmsByDateAndLimit(date, 10);
                    cache.put(date, datesFromDB);
                });
    }
}
