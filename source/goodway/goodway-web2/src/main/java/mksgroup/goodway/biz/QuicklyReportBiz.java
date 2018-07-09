package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.QuicklyReport;

public interface QuicklyReportBiz {
	
	boolean updateQuicklyReports(Iterable<QuicklyReport> quicklyReports, List<Integer> tobeDeletedIds);
    
    CrudRepository<QuicklyReport, Integer> getRepo();
}
