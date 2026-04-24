package uni.miskolc.hu.library_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A cím nem lehet üres")
    private String title;

    @NotBlank(message = "Az ISBN nem lehet üres")
    private String isbn;

    @Min(value = 1000, message = "Érvénytelen kiadási év")
    private Integer publishYear;
    
    private Long authorId; 
    private Long genreId;
    private Long borrowerId;
}