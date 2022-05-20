package ru.malygin.parser_client.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.malygin.parser_client.model.dto.impl.FilmDto;
import ru.malygin.parser_client.repo.FilmRepo;
import ru.malygin.parser_client.service.FilmService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepo filmRepo;
    @Value("${film.show.count:10}")
    private Integer filmToShow;

    @Override
    public List<FilmDto> findFilmsByDateAndLimit(LocalDate date,
                                                 Integer limit) {
        return filmRepo.getTopFilmFromDate(date, PageRequest.of(0, filmToShow, Sort.by("pos")));
    }

    @Override
    public List<LocalDate> getListOfAvailableDate() {
        return filmRepo.getListOfAvailableDate();
    }
}
