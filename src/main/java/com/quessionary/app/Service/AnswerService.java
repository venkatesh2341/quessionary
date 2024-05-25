package com.quessionary.app.Service;

import com.quessionary.app.DTO.AnswerDTO;
import com.quessionary.app.DTO.EditAnswerDTO;
import com.quessionary.app.Models.Answer;
import com.quessionary.app.Models.Question;
import com.quessionary.app.Models.User;
import com.quessionary.app.Repository.AnswerRepository;
import com.quessionary.app.Repository.QuestionRepository;
import com.quessionary.app.Repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {

   final private AnswerRepository answerRepository;
   final private UserRepository userRepository;
   final private QuestionRepository questionRepository;

    AnswerService(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository){
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Answer createAnswer(UUID questionId, AnswerDTO answerDTO)
    {
        Optional<User> user = userRepository.findById(answerDTO.getUserId());
        if(user.isEmpty()) return null;
        Optional<Question> question = questionRepository.findById(questionId);
        if(question.isEmpty()) return null;
        Answer answer = Answer.builder()
                .text(answerDTO.getText())
                .user(user.get())
                .question(question.get())
                .build();
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(UUID answerId, EditAnswerDTO editAnswerDTO)
    {
        Optional<Answer> answer= answerRepository.findById(answerId);
        if(answer.isEmpty())
            return null;
        answer.get().setText(editAnswerDTO.getText());
       return answerRepository.save(answer.get());

    }


}
