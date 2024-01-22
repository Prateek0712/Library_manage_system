package com.accioProj.libraryMgmt.DTOs;

import com.accioProj.libraryMgmt.Enums.genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class bookRequestDTO {
    private String bookName;
    private genre bookGenre;
    private Integer price;
    private Date date;
    private int noOfPages;
    private Integer authorId;
}
