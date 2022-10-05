package repository;

import model.ReportStatus;
import model.VolunteerCalling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerCallingRepository extends JpaRepository <VolunteerCalling,Long> {

    List<VolunteerCalling> findAllByStatusEquals (ReportStatus status);
}
