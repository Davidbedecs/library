package uni.miskolc.hu.library_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uni.miskolc.hu.library_management.dto.BookDTO;
import uni.miskolc.hu.library_management.model.Book;
import uni.miskolc.hu.library_management.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<BookDTO> getAll() {
        return service.getAllBooks();
    }

    @PostMapping
    public BookDTO create(@Valid @RequestBody Book book) {
        return service.saveBook(book);
    }

    @GetMapping("/filter")
    public List<BookDTO> filter(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear) {
        return service.filterBooks(title, minYear, maxYear);
    }
    @PostMapping("/{bookId}/author/{authorId}")
    public BookDTO assignAuthor(@PathVariable Long bookId, @PathVariable Long authorId) {
    return service.assignAuthorToBook(bookId, authorId);
}
    @PostMapping("/{bookId}/genres/{genreId}")
    public BookDTO assignGenre(@PathVariable Long bookId, @PathVariable Long genreId) {
    return service.assignGenreToBook(bookId, genreId);
}
    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id) {
    return service.getBookById(id);
}
}