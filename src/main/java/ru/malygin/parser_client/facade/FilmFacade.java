package ru.malygin.parser_client.facade;

import ru.malygin.parser_client.model.dto.impl.FilmDto;

import java.time.LocalDate;
import java.util.List;

public interface FilmFacade {
    List<LocalDate> getAvailableDates();

    List<FilmDto> getFilmsOnDate(String date);
}
