package uni.miskolc.hu.library_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

import jakarta.validation.Valid;
import uni.miskolc.hu.library_management.dto.BookDTO;
import uni.miskolc.hu.library_management.dto.LibraryMemberDTO;
import uni.miskolc.hu.library_management.model.LibraryMember;
import uni.miskolc.hu.library_management.service.BookService;
import uni.miskolc.hu.library_management.service.LibraryMemberService;

@RestController
@RequestMapping("/library-members")
public class LibraryMemberController {

    @Autowired
    private LibraryMemberService service;

    @Autowired 
    private BookService bookService;

    @GetMapping
    public List<LibraryMemberDTO> getAll() {
        return service.getAllMembers();
    }

    @PostMapping
    public LibraryMemberDTO create(@Valid @RequestBody LibraryMember member) {
        return service.saveMember(member);
    }

    @PostMapping("/{memberId}/borrow/{bookId}")
    public BookDTO borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
    
        return bookService.borrowBook(bookId, memberId);
    }
}