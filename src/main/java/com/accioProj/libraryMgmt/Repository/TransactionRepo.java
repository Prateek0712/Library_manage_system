package com.accioProj.libraryMgmt.Repository;

import com.accioProj.libraryMgmt.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,String> {

}
