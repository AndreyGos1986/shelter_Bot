package jd5.ShelterBot.shelterBot.repository;


import jd5.ShelterBot.shelterBot.model.ParentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий, в котором хранятся все усыновители животных
 */
@Repository
public interface ParentRepository extends JpaRepository<ParentUser, Long> {

    ParentUser findByShelterUserId(long userId);
}
