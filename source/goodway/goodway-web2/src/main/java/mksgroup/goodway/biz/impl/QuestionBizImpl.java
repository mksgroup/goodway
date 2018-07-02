package mksgroup.goodway.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.QuestionBiz;
import mksgroup.goodway.entity.Question;
import mksgroup.goodway.repository.QuestionRepository;

@Service
public class QuestionBizImpl implements QuestionBiz{
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public boolean updateQuestions(Iterable<Question> questions, List<Integer> tobeDeletedIds) {
		
		if(tobeDeletedIds != null) {
			tobeDeletedIds.forEach(deleteId -> {
				questionRepository.deleteById(deleteId);;
			});
		}
		
		questionRepository.saveAll(questions);
		
		return true;
	}

	@Override
	public CrudRepository<Question, Integer> getRepo() {
		return questionRepository;
	}

}
