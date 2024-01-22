package com.accioProj.libraryMgmt.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.*;
@Entity
@Table(name="Author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this will help us to generate Author Id automatically
    private Integer AuthorId;
    private String authorName;
    private int authorAge;

    @Column (unique = true) //this is column property which assign declare property to column written exactly below this
    private String authorEmail;
    private String phoneNo;
    private int noOfPages;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> bookList=new ArrayList<>();

    public Author(String authorName, int authorAge, String authorEmail, String phoneNo) {
        this.authorName = authorName;
        this.authorAge = authorAge;
        this.authorEmail = authorEmail;
        this.phoneNo = phoneNo;
    }
}
