package pl.dog.dogback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dog.dogback.dto.TermDto;
import pl.dog.dogback.service.UserService;

import java.util.List;

import static pl.dog.dogback.ApiPath.DASHBOARD;

@RestController
@RequestMapping(DASHBOARD)
@RequiredArgsConstructor
public class DashboardController {

    private final UserService userService;

    @GetMapping
    List<TermDto> getMyTerms(@RequestParam Long id) {
        return userService.getMyTerms(id);
    }
}
