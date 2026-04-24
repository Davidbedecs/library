package uni.miskolc.hu.library_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryMemberDTO {
    private Long id;
    private String name;
    private String email;
    private String membershipType;
}