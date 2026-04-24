package uni.miskolc.hu.library_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.miskolc.hu.library_management.dto.GenreDTO;
import uni.miskolc.hu.library_management.model.Genre;
import uni.miskolc.hu.library_management.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Autowired
    private GenreRepository repository;

    public List<GenreDTO> getAllGenres() {
        return repository.findAll().stream()
                .map(g -> new GenreDTO(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }

    public GenreDTO saveGenre(Genre genre) {
        Genre saved = repository.save(genre);
        return new GenreDTO(saved.getId(), saved.getName());
    }
}