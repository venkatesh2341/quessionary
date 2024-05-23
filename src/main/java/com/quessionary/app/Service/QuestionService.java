package com.quessionary.app.Service;

import com.quessionary.app.DTO.QuestionDTO;
import com.quessionary.app.Models.Question;
import com.quessionary.app.Models.User;
import com.quessionary.app.Repository.QuestionRepository;
import com.quessionary.app.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    QuestionRepository questionRepository;
    UserRepository userRepository;
    QuestionService(QuestionRepository questionRepository, UserRepository userRepository)
    {
        this.questionRepository= questionRepository;
        this.userRepository=userRepository;
    }

    public Question createQuestion(QuestionDTO questionDTO)
    {
        Optional<User> user= userRepository.findById(questionDTO.getUserId());
        if(user.isEmpty())
        {
            return  null;
        }
        Question question = Question.builder()
               .user(user.get())
                .title(questionDTO.getTitle())
                .body(questionDTO.getBody())
               .build();
        return questionRepository.save(question);
    }
}
