package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.Question;
@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer>{

}
