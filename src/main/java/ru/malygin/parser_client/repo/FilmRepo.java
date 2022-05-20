package ru.malygin.parser_client.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.malygin.parser_client.model.dto.impl.FilmDto;
import ru.malygin.parser_client.model.entity.impl.Film;

import java.time.LocalDate;
import java.util.List;

public interface FilmRepo extends CrudRepository<Film, Long> {

    @Query(value = "select films.insertDate from Film films group by films.insertDate")
    List<LocalDate> getListOfAvailableDate();

    @Query(value = "select distinct new ru.malygin.parser_client.model.dto.impl.FilmDto(films.pos, films.rating, films.title, films.prodYear, films.voteCount) " +
            "from Film films " +
            "where films.insertDate=?1 " +
            "order by films.pos")
    List<FilmDto> getTopFilmFromDate(LocalDate date,
                                     Pageable pageable);
}
