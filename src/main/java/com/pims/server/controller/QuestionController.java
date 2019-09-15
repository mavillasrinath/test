package com.pims.server.controller;

import com.pims.server.commons.ResourceNotFoundException;
import com.pims.server.model.Question;
import com.pims.server.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return questionService.findAll();
    }


    @PostMapping("/questions")
    public Question createQuestion(@Valid @RequestBody Question question) {
        return questionService.save(question);
    }

    @PutMapping("/questions/{questionId}")
    public Question updateQuestion(@PathVariable UUID questionId,
                                   @Valid @RequestBody Question questionRequest) {
        return questionService.findById(questionId)
                .map(question -> {
                    question.setTitle(questionRequest.getTitle());
                    question.setDescription(questionRequest.getDescription());
                    return questionService.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }


    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable UUID questionId) {
        return questionService.findById(questionId)
                .map(question -> {
                	questionService.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }
}
