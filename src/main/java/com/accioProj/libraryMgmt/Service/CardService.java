package com.accioProj.libraryMgmt.Service;

import com.accioProj.libraryMgmt.Entities.Students;
import com.accioProj.libraryMgmt.Entities.libraryCard;
import com.accioProj.libraryMgmt.Enums.cardStatus;
import com.accioProj.libraryMgmt.Repository.CardRepo;
import com.accioProj.libraryMgmt.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private StudentRepo studentRepo;

    public String getFreshCard()
    {
        libraryCard newcard=new libraryCard();
        //now set thing  in that  card
        newcard.setCardstatus(cardStatus.NEW);
        newcard.setNofBookIssue(0);
        //remeber card id will be automatically generated
        libraryCard saveCard= cardRepo.save(newcard);
        // this save method will return same entity
        return "New card generate with Id"+saveCard.getCardId();
    }
    public String AssignLibraryCardToStudent(Integer studentId,Integer cardId) throws Exception
    {
        Optional<libraryCard> Olibrarycard=cardRepo.findById(cardId);
        Optional<Students> Ostudent=studentRepo.findById(studentId);
        if(Olibrarycard.isEmpty())
        {
            throw new Exception("Card ID INVALID");
        }
        if(Ostudent.isEmpty())
        {
            throw new Exception("STUDENT ID INVALID");
        }
        libraryCard librarycard=Olibrarycard.get();
        Students student=Ostudent.get();
        librarycard.setCardstatus(cardStatus.ACTIVE);
        librarycard.setNofBookIssue(0);

        //as this will be birectional mapping  any  parent or child we can save remaining  will be autosave
        librarycard.setStudent(student);
        student.setLibraryCard(librarycard);
        studentRepo.save(student);
        //cardRepo.save(librarycard);
        return "CARD WITH ID--"+cardId+" IS ASSIGN TO STUDENT WITH  ID--"+studentId;
    }
}
