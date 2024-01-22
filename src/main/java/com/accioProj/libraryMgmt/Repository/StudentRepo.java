package com.accioProj.libraryMgmt.Repository;

import com.accioProj.libraryMgmt.Entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Students,Integer> {
    //it will be blank if we are just  using inbult method
}
