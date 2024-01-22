package com.accioProj.libraryMgmt.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class authorRequestDTO {
    private String authorName;
    private int authorAge;
    private String authorEmail;
    private String phoneNo;
}
