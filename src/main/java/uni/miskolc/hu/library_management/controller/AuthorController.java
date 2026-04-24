package uni.miskolc.hu.library_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uni.miskolc.hu.library_management.dto.AuthorDTO;
import uni.miskolc.hu.library_management.model.Author;
import uni.miskolc.hu.library_management.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping
    public List<AuthorDTO> getAll() {
        return service.getAllAuthors();
    }

    @PostMapping
    public AuthorDTO create(@Valid @RequestBody Author author) {
        return service.saveAuthor(author);
    }

    @GetMapping("/filter")
    public List<AuthorDTO> filter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String nationality) {
        return service.filterAuthors(name, minAge, maxAge, nationality);
    }
    @PutMapping("/{id}")
     public AuthorDTO update(@PathVariable Long id, @Valid @RequestBody Author authorDetails) {
    return service.updateAuthor(id, authorDetails);
}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    service.deleteAuthor(id);
}
    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable Long id) {
    return service.getAuthorById(id);
}
}