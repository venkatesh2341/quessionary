package com.quessionary.app.Controller;

import com.quessionary.app.DTO.QuestionDTO;
import com.quessionary.app.Models.Question;
import com.quessionary.app.Repository.QuestionRepository;
import com.quessionary.app.Service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    final private QuestionService questionService;

    QuestionController(QuestionService questionService)
    {
        this.questionService=questionService;
    }

    @PostMapping("/create")
    ResponseEntity<?> createQuestion(@RequestBody QuestionDTO questionDTO)
    {
        Question question = questionService.createQuestion(questionDTO);
        if(question!= null)
            return new ResponseEntity<Question>(question, HttpStatus.CREATED);

        return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/")
    ResponseEntity<?> getAllQuestions()
    {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
    }
}
