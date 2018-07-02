package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.Question;

public interface QuestionBiz {	
	
	boolean updateQuestions(Iterable<Question> questions, List<Integer> tobeDeletedIds);
    
    CrudRepository<Question, Integer> getRepo();
}
