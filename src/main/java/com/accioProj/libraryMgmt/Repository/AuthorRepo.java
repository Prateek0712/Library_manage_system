package com.accioProj.libraryMgmt.Repository;

import com.accioProj.libraryMgmt.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Integer> {
    //it will be blank if we are just  using inbult method
}
