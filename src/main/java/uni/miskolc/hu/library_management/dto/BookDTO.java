package uni.miskolc.hu.library_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private Integer publishYear;
    private Long authorId;
    private Long genreId;
    private Long borrowerId;
}