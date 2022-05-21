package ru.malygin.parser_client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.malygin.parser_client.facade.FilmFacade;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {

    private final FilmFacade filmFacade;

    @Value("${spring.profile.active:prod}")
    private String profile;

    @GetMapping
    public String getIndex(Model model) {

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("dates", filmFacade.getAvailableDates());
        return "index";
    }
}
