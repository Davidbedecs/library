package uni.miskolc.hu.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.miskolc.hu.library_management.model.LibraryMember;

@Repository
public interface LibraryMemberRepository extends JpaRepository<LibraryMember, Long> {
}