package jd5.ShelterBot.shelterBot.repository;

import jd5.ShelterBot.shelterBot.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;


public interface ReportRepository extends JpaRepository<Report, Long> {
    Report getReportByParentIdAndDate(Long parentId, LocalDate date);
    List<Report> findAllByParentId(Long parentId);

}
