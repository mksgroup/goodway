package mksgroup.goodway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Question;
import mksgroup.goodway.repository.QuestionRepository;

@Controller
public class QuestionController {
	
	/** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    private QuestionRepository QuestionRepository;
    
    @RequestMapping("/question/new")
    public String questionNew() {
        return "question/new";
    }
    
    /**Load danh sach question
     * 
     * @return
     */
    @GetMapping("/question/load-question")
    @ResponseBody
    public Iterable<Question> loadQuestion(){
    	Iterable<Question> question = QuestionRepository.findAll();
    	
    	return question;
    }
}
