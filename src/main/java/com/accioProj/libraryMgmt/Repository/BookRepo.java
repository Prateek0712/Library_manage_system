package com.accioProj.libraryMgmt.Repository;

import com.accioProj.libraryMgmt.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {
    //it will be blank if we are just  using inbult method
}
