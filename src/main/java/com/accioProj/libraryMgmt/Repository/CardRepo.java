package com.accioProj.libraryMgmt.Repository;

import com.accioProj.libraryMgmt.Entities.libraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository <libraryCard,Integer>{
    //it will be blank if we are just  using inbult method
}
