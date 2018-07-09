package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.QuicklyReport;

@Repository
public interface QuicklyReportRepository extends CrudRepository<QuicklyReport, Integer>{

}
