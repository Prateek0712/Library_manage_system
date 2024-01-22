package com.accioProj.libraryMgmt.Service;

import com.accioProj.libraryMgmt.DTOs.authorRequestDTO;
import com.accioProj.libraryMgmt.Entities.Author;
import com.accioProj.libraryMgmt.Repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private JavaMailSender mailSender; //this is inbuilt class repo in  java to send mail

    public String addAuthorByDto(authorRequestDTO authorDto)
    {
        Author author =new Author(authorDto.getAuthorName(),authorDto.getAuthorAge(),authorDto.getAuthorEmail(),authorDto.getPhoneNo());
        authorRepo.save(author);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Hi "+author.getAuthorName()+" !");

        message.setFrom("pustakalay7120@gmail.com");
        message.setTo(author.getAuthorEmail());


        message.setText("You have been successfully Registered on our portal !" +
                "Looking forward for adding more books ");

        mailSender.send(message);
        return  "NEW AUTHOR ADDED SUCCESSFULLY";
    }
}
