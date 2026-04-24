package uni.miskolc.hu.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.miskolc.hu.library_management.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}