package mksgroup.goodway.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.biz.QuicklyReportBiz;
import mksgroup.goodway.entity.QuicklyReport;
import mksgroup.goodway.model.QuicklyReportModel;
import mksgroup.goodway.util.AppUtil;

@Controller
public class QuicklyReportController extends BaseController{
	 	private final static Logger LOG = LoggerFactory.getLogger(QuicklyReportController.class);

	    @Autowired
	    private QuicklyReportBiz quicklyReportBiz;

	    /**
	     * Go to quickly report's index page.
	     * @return
	     */
	    @GetMapping({"/quicklyReport", "/quicklyReport/new"})
	    public String goQuickLySearch() {

	        return "quicklyReport/new";
	    }
	    
	    /**
	     * 
	     * Load quickly report list.
	     * @return
	     */
	    @GetMapping("/quicklyReport/load-quicklyReport")
	    @ResponseBody()
	    public Iterable<QuicklyReport> loadQuicklyReports(){
	        
	    	Iterable<QuicklyReport> listQuicklyReports = quicklyReportBiz.getRepo().findAll();
	        return listQuicklyReports;
	    }

	    @PostMapping("/quicklyReport/save")
	    @ResponseBody
	    public Iterable<QuicklyReport> saveQuicklyReports(@Valid @RequestBody QuicklyReportModel data, Errors errors, HttpServletRequest request) {
	        LOG.info("saveQuicklyReports....");
	        
	        // If error, just return a 400 bad request, along with the error message
	        if (errors.hasErrors()) {

	            LOG.error(errors.getAllErrors()
	                        .stream().map(x -> x.getDefaultMessage())
	                        .collect(Collectors.joining(",")));

	            return null;
	        } else {
	        	LOG.info(data.toString());
	            Iterable<QuicklyReport> entities = AppUtil.parseQuicklyReport(data);
	            LOG.info("Step complete");
	            quicklyReportBiz.updateQuicklyReports(entities, data.getDeletedIds());
	        }
	        
	        // Reload data from db
	        Iterable<QuicklyReport> orders = quicklyReportBiz.getRepo().findAll();
	        
	        return orders;
	    }
	    @GetMapping("/quicklyReport/export")
	    @ResponseBody
	    public void downloadQuicklyReport(HttpServletResponse response) {
	        LOG.debug("download quickly report....");
	        
	        try {
	            downloadExcel(response);
	        } catch (IOException ex) {
	            LOG.error("Could not download quickly report.", ex);
	        }
	    }

	    /**
	     * Downloaded file name for quickly report.
	     * @return
	     * @see mksgroup.goodway.controller.BaseController#getFilename()
	     */
	    @Override
	    String getFilename() {
	        return "QuicklyReport.xlsx";
	    }

	    /**
	     * Template for downloading quickly reports.
	     * @return
	     * @see mksgroup.goodway.controller.BaseController#getTemplate()
	     */
	    @Override
	    String getTemplate() {
	        return "/excel-templates/Template_Quickly_Report.xlsx";
	    }

	    /**
	     * Get all quickly report for downloading.
	     * @return
	     * @see mksgroup.goodway.controller.BaseController#getDownloadData()
	     */
	    @Override
	    Iterable<?> getDownloadData() {
	        return quicklyReportBiz.getRepo().findAll();
	    }
}
