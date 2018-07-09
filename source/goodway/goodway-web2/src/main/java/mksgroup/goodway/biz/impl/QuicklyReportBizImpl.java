package mksgroup.goodway.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.QuicklyReportBiz;
import mksgroup.goodway.entity.QuicklyReport;
import mksgroup.goodway.repository.QuicklyReportRepository;

@Service
public class QuicklyReportBizImpl implements QuicklyReportBiz{

	@Autowired
	 private QuicklyReportRepository quicklyReportRepository;

	 @Override
	 public boolean updateQuicklyReports(Iterable<QuicklyReport> quicklyReports, List<Integer> tobeDeletedIds) {
	        
	        if (tobeDeletedIds != null) {
	            tobeDeletedIds.forEach(deletedId -> {
	            	quicklyReportRepository.deleteById(deletedId);
	            });
	        }
	        
	        quicklyReportRepository.saveAll(quicklyReports);

	        return true;
	    }

	    @Override
	    public CrudRepository<QuicklyReport, Integer> getRepo() {
	        return quicklyReportRepository;
	    }
}
