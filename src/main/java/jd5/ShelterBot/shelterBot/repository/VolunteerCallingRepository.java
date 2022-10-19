package jd5.ShelterBot.shelterBot.repository;

import jd5.ShelterBot.shelterBot.model.ReportStatus;
import jd5.ShelterBot.shelterBot.model.VolunteerCalling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Репозиторий, в котором хранятся вызовы волонтёров
 */
@Repository
public interface VolunteerCallingRepository extends JpaRepository <VolunteerCalling,Long> {

    List<VolunteerCalling> findAllByStatusEquals (ReportStatus status);
}