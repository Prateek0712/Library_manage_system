package com.accioProj.libraryMgmt.Service;

import com.accioProj.libraryMgmt.Entities.Students;
import com.accioProj.libraryMgmt.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public String addStudent(Students student)
    {
        studentRepo.save(student);
        return "NEW STUUDENT ADDED SUCCESFULLY";
    }
}
