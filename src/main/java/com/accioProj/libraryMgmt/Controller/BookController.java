package com.accioProj.libraryMgmt.Controller;

import com.accioProj.libraryMgmt.DTOs.bookRequestDTO;
import com.accioProj.libraryMgmt.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBookByDto")
    public ResponseEntity addBookByDto(@RequestBody bookRequestDTO bookDto)
    {
        String  resp=bookService.addBookByDto(bookDto);
        return  new ResponseEntity(resp, HttpStatus.ACCEPTED);
    }
}
