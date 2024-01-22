package com.accioProj.libraryMgmt.Controller;

import com.accioProj.libraryMgmt.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/generateCard")
    public String addCard()
    {
        String result= cardService.getFreshCard();
        return result;
    }
    @GetMapping("/AssignLibraryCard")
    public ResponseEntity AssociateCard (@RequestParam("studentId")Integer sId,
                                         @RequestParam("cardID") Integer cId)
    {
        try
        {
            String resp=cardService.AssignLibraryCardToStudent(sId,cId);
            return new ResponseEntity<>(resp,HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
