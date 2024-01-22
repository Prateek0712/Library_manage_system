package com.accioProj.libraryMgmt.Controller;

import com.accioProj.libraryMgmt.DTOs.authorRequestDTO;
import com.accioProj.libraryMgmt.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthorByDto")
    public ResponseEntity addAuthorByDto(@RequestBody authorRequestDTO authorDTO)
    {
        String resp= authorService.addAuthorByDto(authorDTO);
        return  new ResponseEntity<>(resp, HttpStatus.ACCEPTED);
    }
}
