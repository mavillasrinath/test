package com.pims.server.controller;

import com.pims.server.commons.ResourceNotFoundException;
import com.pims.server.model.Answer;
import com.pims.server.service.AnswerService;
import com.pims.server.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class AnswerController {

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private AnswerService answerService;
    
    @GetMapping("/questions/{questionId}/answers")
    public List<Answer> getAnswersByQuestionId(@PathVariable UUID questionId) {
        return answerService.findByQuestionId(questionId);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswer(@PathVariable UUID questionId,
                            @Valid @RequestBody Answer answer) {
        return questionService.findById(questionId)
                .map(question -> {
                    answer.setQuestion(question);
                    return answerService.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public Answer updateAnswer(@PathVariable UUID questionId,
                               @PathVariable UUID answerId,
                               @Valid @RequestBody Answer answerRequest) {
        if(!questionService.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        return answerService.findById(answerId)
                .map(answer -> {
                    answer.setText(answerRequest.getText());
                    return answerService.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable UUID questionId,
                                          @PathVariable UUID answerId) {
        if(!questionService.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        return answerService.findById(answerId)
                .map(answer -> {
                    answerService.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));

    }
}
