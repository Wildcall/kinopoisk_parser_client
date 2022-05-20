package ru.malygin.parser_client.service;

import ru.malygin.parser_client.model.dto.impl.FilmDto;

import java.time.LocalDate;
import java.util.List;

public interface FilmService {
    List<FilmDto> findFilmsByDateAndLimit(LocalDate date,
                                          Integer limit);

    List<LocalDate> getListOfAvailableDate();

}
