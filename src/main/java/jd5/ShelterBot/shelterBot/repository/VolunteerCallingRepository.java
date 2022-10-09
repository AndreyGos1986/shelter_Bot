package jd5.ShelterBot.shelterBot.repository;

import jd5.ShelterBot.shelterBot.model.ReportStatus;
import jd5.ShelterBot.shelterBot.model.VolunteerCalling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Репозиторий, в котором хранятся вызовы волонтёров
 */
public interface VolunteerCallingRepository extends JpaRepository <VolunteerCalling,Long> {

    List<VolunteerCalling> findAllByStatusEquals (ReportStatus status);
}
