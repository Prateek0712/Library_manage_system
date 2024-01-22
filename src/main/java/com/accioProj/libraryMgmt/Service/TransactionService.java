package com.accioProj.libraryMgmt.Service;

import com.accioProj.libraryMgmt.Entities.Book;
import com.accioProj.libraryMgmt.Entities.Transaction;
import com.accioProj.libraryMgmt.Entities.libraryCard;
import com.accioProj.libraryMgmt.Enums.TransactionStatus;
import com.accioProj.libraryMgmt.Enums.TransactionType;
import com.accioProj.libraryMgmt.Repository.BookRepo;
import com.accioProj.libraryMgmt.Repository.CardRepo;
import com.accioProj.libraryMgmt.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;
import javax.xml.datatype.DatatypeConstants;
import java.time.*;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    public String issueBook(Integer cardId, Integer bookId) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.Issue);
        transaction.setTransactionStatus(TransactionStatus.Ongoing);
        transaction.setCreatedOn(LocalDateTime.now());
        //1. Get the book and card Entity from DB

        Optional<Book> bookOptional = bookRepo.findById(bookId);

        if(bookOptional.isEmpty()){
            throw new Exception("BookId entered is invalid");
        }

        Optional<libraryCard> optionalLibraryCard = cardRepo.findById(cardId);

        if(optionalLibraryCard.isEmpty()){
            throw new Exception("Card Id Entered is Invalid");
        }

        Book book = bookOptional.get();
        libraryCard card = optionalLibraryCard.get();
        //2. validate book and card Entity variables

        //Check for availability
        if(book.isAvailable()==Boolean.FALSE){
            transaction.setTransactionStatus(TransactionStatus.Failure);
            transaction = transactionRepo.save(transaction);
            throw new Exception("Book with the bookId is not available. TransactionId "+transaction.getTransactionId());
        }
        //Check for max book issued
        if(card.getNofBookIssue()>=libraryCard.MaxBookAllow){
            transaction.setTransactionStatus(TransactionStatus.Failure);
            transaction = transactionRepo.save(transaction);
            throw new Exception("You have reached the max limit of issed books" +
                    "please return a book in order to issue new " +
                    "Transaction Id"+transaction.getTransactionId());
        }


        //If you have reached that means all validations are OK

        transaction.setTransactionStatus(TransactionStatus.Success);

        //3. update the card and the book status
        book.setAvailable(Boolean.FALSE);
        card.setNofBookIssue(card.getNofBookIssue()+1);
        transaction.setBook(book);
        transaction.setLibraryCard(card);
        //Save the child as it will cascade to both of the Parents
        transaction = transactionRepo.save(transaction);

        return "The transaction with Id"+transaction.getTransactionId()+" has been saved to the DB"+" "+book.getBookId()+" "+book.getBookName()+" "+book.getTransactionList().size();

    }
    public String returnBook(Integer cardId,Integer bookId) throws Exception
    {
       Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.Ongoing);
        transaction.setTransactionType(TransactionType.Return);
        transaction.setCreatedOn(LocalDateTime.now());
        Optional<Book> OptionalBook = bookRepo.findById(bookId);
        if (OptionalBook.isEmpty()) {
            throw new Exception("Book ID is Invalid");
        }
        Book book = OptionalBook.get();
        Optional<libraryCard> OptionalCard = cardRepo.findById(cardId);
        if (OptionalBook.isEmpty()) {
            throw new Exception("Invalid card ID");
        }
        libraryCard card = OptionalCard.get();
        List<Transaction> transactionList=card.getTransactionList();
        Transaction issueTransaction=null;
        for(Transaction t:transactionList)
        {
            if(t.getBook().getBookId()==bookId)
            {
                //System.out.println(t.getBook().getBookName());
                issueTransaction=t;
            }
        }
        if(issueTransaction==null)
        {
            throw new Exception("THIS IS NOT ALLOTED TO YOU.......CHORRRR KAHIKE......LAUTA DE JISKI HE USSE");
        }
        transaction.setBook(book);
        transaction.setLibraryCard(card);
        //so at end we will have latest transaction  for  this book against  this card
        LocalDateTime issueDate= issueTransaction.getCreatedOn();
        LocalDateTime ReturndDate= transaction.getCreatedOn();
        Period p=Period.between(issueDate.toLocalDate(),ReturndDate.toLocalDate());
        int Days=p.getDays();
        if(Days>20)
        {
            int fine= (Days-20)*100;
            transaction.setFineAmount(fine);
        }
        book.setAvailable(Boolean.TRUE);
        card.setNofBookIssue(card.getNofBookIssue()-1);
        transaction.setTransactionStatus(TransactionStatus.Success);
        transactionRepo.save(transaction);
        return "BOOK IS SUBMITTED WITH FINE AMOUNT OF RS. "+transaction.getFineAmount();
    }
}
