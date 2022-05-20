package ru.malygin.parser_client.service;

import ru.malygin.parser_client.model.dto.impl.FilmDto;

import java.time.LocalDate;
import java.util.List;

public interface FilmCache {
    String create();

    String update();

    List<FilmDto> getFilmsOnDate(LocalDate date);
}
