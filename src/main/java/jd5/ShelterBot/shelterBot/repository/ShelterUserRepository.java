package jd5.ShelterBot.shelterBot.repository;

import jd5.ShelterBot.shelterBot.model.ShelterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterUserRepository extends JpaRepository<ShelterUser, Long> {

}