package com.quessionary.app.Controller;

import com.quessionary.app.DTO.AnswerDTO;
import com.quessionary.app.DTO.EditAnswerDTO;
import com.quessionary.app.Models.Answer;
import com.quessionary.app.Service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AnswerController {

    private AnswerService answerService;

    AnswerController(AnswerService answerService){
        this.answerService= answerService;
    }

    @PostMapping("/questions/{questionId}/answers")
    ResponseEntity<?> createAnswer(@PathVariable UUID questionId, @RequestBody AnswerDTO answerDTO){

        Answer answer= answerService.createAnswer(questionId, answerDTO);
        if(answer!=null)
            return new ResponseEntity<Answer>(answer,HttpStatus.CREATED);
        return new ResponseEntity<String>("User or question not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("answers/{answerId}")
    ResponseEntity<?> updateAnswer(@PathVariable UUID answerId, @RequestBody EditAnswerDTO editAnswerDTO)
    {
        Answer answer = answerService.updateAnswer(answerId, editAnswerDTO);
        if(answer != null)
            return new ResponseEntity<Answer>(answer,HttpStatus.OK);
        return new ResponseEntity<String>("Answer not found",HttpStatus.NOT_FOUND);

    }
}
