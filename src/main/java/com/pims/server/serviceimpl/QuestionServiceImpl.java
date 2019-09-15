package com.pims.server.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pims.server.model.Question;
import com.pims.server.repository.QuestionRepository;
import com.pims.server.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Optional<Question> findById(UUID questionId) {
		// TODO Auto-generated method stub
		return questionRepository.findById(questionId);
	}

	@Override
	public boolean existsById(UUID questionId) {
		// TODO Auto-generated method stub
		return questionRepository.existsById(questionId);
	}

	@Override
	public List<Question> findAll() {
		// TODO Auto-generated method stub
		return questionRepository.findAll();
	}

	@Override
	public Question save(@Valid Question question) {
		// TODO Auto-generated method stub
		return questionRepository.save(question);
	}

	@Override
	public void delete(Question question) {
		// TODO Auto-generated method stub
		questionRepository.delete(question);
		
	}

}
