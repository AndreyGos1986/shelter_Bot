package repository;

import model.Report;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;


/**
 * Репозиторий, в котором хранятся доклады усыновителей
 */
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report getReportByParentIdAndDate(Long parentId, LocalDate date);
    List<Report> findAllByParentId(Long parentId);

}
