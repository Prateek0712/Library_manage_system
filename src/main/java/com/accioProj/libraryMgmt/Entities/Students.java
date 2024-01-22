package com.accioProj.libraryMgmt.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //annotation  to let know spring jpa that this is entity and u have to create table for that
@Table(name = "Student")
@Getter //this will generate getter automatically
@Setter //this will generate setter automatically
@NoArgsConstructor //this will generate no args constructor  automatically
@AllArgsConstructor //this will generate all args constructor automatically
public class Students {
    @Id //annotation for setting up a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RollNo;
    private  String  name;
    private String branch;
    private  double cgpa;
    private  String phoneNo;
    //In case of unidirectional mapping 2 line follow by this are not required
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL) //mapped is variable name which we have declare in fKey of child table
    private libraryCard libraryCard;
}
