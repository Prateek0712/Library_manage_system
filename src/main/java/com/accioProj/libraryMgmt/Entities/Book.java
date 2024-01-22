package com.accioProj.libraryMgmt.Entities;

import com.accioProj.libraryMgmt.Enums.genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer BookId;

    @Column(unique = true)
    private String bookName;

    @Enumerated(value = EnumType.STRING)
    private genre bookGenre;
    private Integer price;
    private Date date;
    private int noOfPages;
    private boolean isAvailable;
    @JoinColumn //it defines there is join on this column which declare in parent and this is child
    @ManyToOne //this specifies rltn between parent and child for 1 author there  might be multiple book
    private Author author ; //this will take private key of declared entity automatically

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();

    public Book(String bookName, genre bookGenre, Integer price, Date date, int noOfPages) {
        this.bookName = bookName;
        this.bookGenre = bookGenre;
        this.price = price;
        this.date = date;
        this.noOfPages = noOfPages;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
