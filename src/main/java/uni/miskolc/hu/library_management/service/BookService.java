package uni.miskolc.hu.library_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.miskolc.hu.library_management.dto.BookDTO;
import uni.miskolc.hu.library_management.model.Book;
import uni.miskolc.hu.library_management.model.exception.EntityNotFoundException;
import uni.miskolc.hu.library_management.repository.BookRepository;
@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookDTO> getAllBooks() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO saveBook(Book book) {
        Book saved = repository.save(book);
        return convertToDTO(saved);
    }

    private BookDTO convertToDTO(Book book) {
    return new BookDTO(
        book.getId(), 
        book.getTitle(), 
        book.getIsbn(), 
        book.getPublishYear(), 
        book.getAuthorId(), 
        book.getGenreId(), 
        book.getBorrowerId()
    );
}
    public List<BookDTO> filterBooks(String title, Integer minYear, Integer maxYear) {
    return repository.findAll().stream()
            .filter(b -> title == null || b.getTitle().toLowerCase().contains(title.toLowerCase()))
            .filter(b -> minYear == null || b.getPublishYear() >= minYear)
            .filter(b -> maxYear == null || b.getPublishYear() <= maxYear)
            .map(this::convertToDTO)
            .collect(Collectors.toList());
}
public BookDTO assignAuthorToBook(Long bookId, Long authorId) {
    
    Book book = repository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("A könyv nem található: " + bookId));
    
    book.setAuthorId(authorId);
    
    Book savedBook = repository.save(book);
    
    return convertToDTO(savedBook);
}
    public BookDTO assignGenreToBook(Long bookId, Long genreId) {
    Book book = repository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("A könyv nem található: " + bookId));
    
    book.setGenreId(genreId);
    Book savedBook = repository.save(book);
    
    return convertToDTO(savedBook);
}
    public BookDTO borrowBook(Long bookId, Long memberId) {
    Book book = repository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("A könyv nem található: " + bookId));
    
    book.setBorrowerId(memberId);
    Book savedBook = repository.save(book);
    
    return convertToDTO(savedBook);
}
public BookDTO getBookById(Long id) {
    return repository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new EntityNotFoundException("A kért könyv nem található! ID: " + id));
}
}