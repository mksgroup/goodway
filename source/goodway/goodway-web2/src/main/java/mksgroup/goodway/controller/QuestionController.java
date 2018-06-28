package mksgroup.goodway.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Question;
import mksgroup.goodway.model.QuestionModel;
import mksgroup.goodway.repository.QuestionRepository;
import mksgroup.goodway.util.AppUtil;

@Controller
public class QuestionController extends BaseController {
	
	/** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    private QuestionRepository questionRepository;
    
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
    	Iterable<Question> question = questionRepository.findAll();
    	
    	return question;
    }
    
    @PostMapping("/question/save")
    @ResponseBody
    public Iterable<Question> saveQuestion(@Valid @RequestBody QuestionModel data, Errors errors, HttpServletRequest request) {
        LOG.info("saveQuestions....");
        
        // If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            LOG.error(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
        LOG.info("step1");
            return null;
        } else {
        	LOG.info("step2");
            Iterable<Question> entities = AppUtil.parseQuestion(data);
            List<Question> entityList = new ArrayList<Question>();

            entities.forEach(e-> entityList.add(e));
            List<Question> question = (List<Question>) questionRepository.findAll();
            for(Question q : question) {
            	if(!entityList.contains(q)) {
            		questionRepository.delete(q);
            	} else {
            		LOG.info("step3");
            		q.setCreated(new Date());
            	}
            }
            
            questionRepository.saveAll(entities);
            LOG.info("questionModel=" + data + ";request=" + request);
        }
        
        // Reload data from db
        Iterable<Question> questions = questionRepository.findAll();
        
        return questions;
    }
    
    @GetMapping("/question/download")
    @ResponseBody
    public String downloadQuestion(HttpServletResponse response) {
        LOG.debug("download questions....");
        
        try {
            downloadExcel(response);
        } catch (IOException ex) {
            LOG.error("Could not download question.", ex);
            return ex.getMessage();
        }

        
        return "Downloaded";
    }

    /**
     * Downloaded file name for question.
     * @return
     * @see mksgroup.goodway.controller.BaseController#getFilename()
     */
    @Override
    String getFilename() {
        return "Question.xlsx";
    }

    /**
     * Template for downloading quetions.
     * @return
     * @see mksgroup.goodway.controller.BaseController#getTemplate()
     */
    @Override
    String getTemplate() {
        return "/excel-templates/Template_Question.xlsx";
    }

    /**
     * Get all question for downloading.
     * @return
     * @see mksgroup.goodway.controller.BaseController#getDownloadData()
     */
    @Override
    Iterable<?> getDownloadData() {
        return questionRepository.findAll();
    }
}
