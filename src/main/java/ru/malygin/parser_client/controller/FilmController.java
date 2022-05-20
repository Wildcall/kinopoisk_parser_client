package ru.malygin.parser_client.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.malygin.parser_client.facade.FilmFacade;
import ru.malygin.parser_client.model.dto.impl.FilmDto;
import ru.malygin.parser_client.model.dto.view.View;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/api/v1/film")
public class FilmController {

    private final FilmFacade filmFacade;

    @GetMapping(path = "/date", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonFormat(pattern = "yyyy-MM-dd")
    public ResponseEntity<List<LocalDate>> findAvailableDate() {
        List<LocalDate> response = filmFacade.getAvailableDates();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(path = "/top", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView(View.Response.class)
    public ResponseEntity<List<FilmDto>> findAvailableDate(@RequestParam(required = false) String date) {
        List<FilmDto> response = filmFacade.getFilmsOnDate(date);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
