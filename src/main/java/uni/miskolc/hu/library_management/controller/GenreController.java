package uni.miskolc.hu.library_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uni.miskolc.hu.library_management.dto.GenreDTO;
import uni.miskolc.hu.library_management.model.Genre;
import uni.miskolc.hu.library_management.service.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService service;

    @GetMapping
    public List<GenreDTO> getAll() {
        return service.getAllGenres();
    }

    @PostMapping
    public GenreDTO create(@Valid @RequestBody Genre genre) {
        return service.saveGenre(genre);
    }
}