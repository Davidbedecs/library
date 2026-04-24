package uni.miskolc.hu.library_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "library_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A név kötelező")
    private String name;

    @Email(message = "Érvénytelen e-mail formátum")
    @NotBlank(message = "Az e-mail nem lehet üres")
    private String email;

    private String membershipType;
}