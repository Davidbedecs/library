package uni.miskolc.hu.library_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.miskolc.hu.library_management.dto.AuthorDTO;
import uni.miskolc.hu.library_management.model.Author;
import uni.miskolc.hu.library_management.model.exception.InvalidDataException;
import uni.miskolc.hu.library_management.model.exception.ResourceNotFoundException;
import uni.miskolc.hu.library_management.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public List<AuthorDTO> getAllAuthors() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO saveAuthor(Author author) {
    
    if (author.getAge() < 0) {
        throw new InvalidDataException("Az életkor nem lehet negatív: " + author.getAge());
    }

    
    Author saved = repository.save(author);
    return convertToDTO(saved);
}
    public List<AuthorDTO> filterAuthors(String name, Integer minAge, Integer maxAge, String nationality) {
        return repository.findAll().stream()
                .filter(a -> name == null || a.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(a -> minAge == null || a.getAge() >= minAge)
                .filter(a -> maxAge == null || a.getAge() <= maxAge)
                .filter(a -> nationality == null || (a.getNationality() != null && a.getNationality().equalsIgnoreCase(nationality)))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AuthorDTO convertToDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getAge(), author.getNationality());
    }
    public AuthorDTO updateAuthor(Long id, Author authorDetails) {
    
    Author author = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Szerző nem található: " + id));

    author.setName(authorDetails.getName());
    author.setAge(authorDetails.getAge());
    author.setNationality(authorDetails.getNationality());

    Author updatedAuthor = repository.save(author);
    
    return new AuthorDTO(updatedAuthor.getId(), updatedAuthor.getName(), updatedAuthor.getAge(), updatedAuthor.getNationality());
}
    public void deleteAuthor(Long id) {
    
    if (!repository.existsById(id)) {
        throw new RuntimeException("Nem törölhető, mert a szerző nem található: " + id);
    }
    repository.deleteById(id);
}
  public AuthorDTO getAuthorById(Long id) {
    Author author = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Szerző nem található ezzel az azonosítóval: " + id));
    
    return new AuthorDTO(author.getId(), author.getName(), author.getAge(), author.getNationality());
}

}