package com.accioProj.libraryMgmt.Service;

import com.accioProj.libraryMgmt.Controller.AuthorController;
import com.accioProj.libraryMgmt.DTOs.bookRequestDTO;
import com.accioProj.libraryMgmt.Entities.Author;
import com.accioProj.libraryMgmt.Entities.Book;
import com.accioProj.libraryMgmt.Repository.AuthorRepo;
import com.accioProj.libraryMgmt.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookRepo bookRepo;

    public String addBookByDto(bookRequestDTO bookDto)
    {
        Book book=new Book(bookDto.getBookName(),bookDto.getBookGenre(),
                bookDto.getPrice(),bookDto.getDate(),bookDto.getNoOfPages());
        book.setAvailable(Boolean.TRUE);
        //set  essential attribute done

        //add book  to autor list, increases no of  book by  author &  add author to book
        Optional<Author> optionalAuthor=authorRepo.findById(bookDto.getAuthorId());
        Author author=optionalAuthor.get();
        book.setAuthor(author);
        author.getBookList().add(book);
        author.setNoOfPages(author.getNoOfPages()+1);
        authorRepo.save(author);
        return "BOOK ADDED SUCCESSFULLY";
    }
}
