package jd5.ShelterBot.shelterBot.repository;

import jd5.ShelterBot.shelterBot.model.CallVolunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<CallVolunteer, Long> {


}
