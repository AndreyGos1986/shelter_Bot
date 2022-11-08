package jd5.ShelterBot.shelterBot.repository;


import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.model.ShelterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий, в котором хранятся все пользователи, написавшие боту
 */
@Repository
public interface UserRepository extends JpaRepository<ShelterUser, Long> {

    ShelterUser findUserByTelegramId(long telegramId);

    List<ShelterUser> findAllByType(ShelterType type);

}
