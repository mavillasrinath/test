package com.pims.server.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pims.server.model.Answer;
import com.pims.server.repository.AnswerRepository;
import com.pims.server.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	private AnswerRepository answerRepository;
	
	
	@Override
	public List<Answer> findByQuestionId(UUID questionId) {
		// TODO Auto-generated method stub
		return answerRepository.findByQuestionId(questionId);
	}


	@Override
	public Optional<Answer> findById(UUID answerId) {
		// TODO Auto-generated method stub
		return answerRepository.findById(answerId);
	}


	@Override
	public void delete(Answer answer) {
		// TODO Auto-generated method stub
		answerRepository.delete(answer);
		
	}


	@Override
	public Answer save(Answer answer) {
		// TODO Auto-generated method stub
		return answerRepository.save(answer);
	}

}
