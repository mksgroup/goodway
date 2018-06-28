package mksgroup.goodway.repository;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer>{

}
