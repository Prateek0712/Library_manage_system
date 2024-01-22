package com.accioProj.libraryMgmt.Entities;

import com.accioProj.libraryMgmt.Enums.cardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="libraryCard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class libraryCard {
    public static final Integer MaxBookAllow=3;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;
    @Enumerated (value = EnumType.STRING)
    private cardStatus cardstatus;
    private int nofBookIssue;

    //this lines of  code will remain same for  unidirctional and  birectional as this is child clsss
    @JoinColumn
    @OneToOne
    private Students student;

    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();
}
