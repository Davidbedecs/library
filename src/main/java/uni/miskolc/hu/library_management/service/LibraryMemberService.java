package uni.miskolc.hu.library_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.miskolc.hu.library_management.dto.LibraryMemberDTO;
import uni.miskolc.hu.library_management.model.LibraryMember;
import uni.miskolc.hu.library_management.repository.LibraryMemberRepository;

@Service
public class LibraryMemberService {

    @Autowired
    private LibraryMemberRepository repository;

    public List<LibraryMemberDTO> getAllMembers() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LibraryMemberDTO saveMember(LibraryMember member) {
        LibraryMember saved = repository.save(member);
        return convertToDTO(saved);
    }

    private LibraryMemberDTO convertToDTO(LibraryMember member) {
        return new LibraryMemberDTO(
            member.getId(), 
            member.getName(), 
            member.getEmail(), 
            member.getMembershipType()
        );
    }
}