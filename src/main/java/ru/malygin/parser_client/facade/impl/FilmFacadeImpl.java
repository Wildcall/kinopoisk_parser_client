package ru.malygin.parser_client.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.malygin.parser_client.exception.BadRequestException;
import ru.malygin.parser_client.facade.FilmFacade;
import ru.malygin.parser_client.model.dto.impl.FilmDto;
import ru.malygin.parser_client.service.DateCache;
import ru.malygin.parser_client.service.FilmCache;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FilmFacadeImpl implements FilmFacade {

    private final FilmCache filmCache;
    private final DateCache dateCache;

    @Override
    public List<LocalDate> getAvailableDates() {
        return dateCache.getAvailableDates();
    }

    @Override
    public List<FilmDto> getFilmsOnDate(String date) {
        if (date == null)
            return filmCache.getFilmsOnDate(LocalDate.now());
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return filmCache.getFilmsOnDate(localDate);
        } catch (DateTimeParseException e) {
            throw new BadRequestException("Wrong date format, use yyyy-MM-dd");
        }

    }
}
