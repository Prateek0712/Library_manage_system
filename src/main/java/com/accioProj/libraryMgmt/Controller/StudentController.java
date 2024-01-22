package com.accioProj.libraryMgmt.Controller;

import com.accioProj.libraryMgmt.Entities.Students;
import com.accioProj.libraryMgmt.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
     @PostMapping("/addStudent")
    public ResponseEntity<String> addCard(@RequestBody Students student)
     {
         String result = studentService.addStudent(student);
         return new ResponseEntity<>(result, HttpStatus.CREATED);
     }
}

