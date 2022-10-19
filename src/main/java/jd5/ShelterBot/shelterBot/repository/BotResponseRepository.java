package jd5.ShelterBot.shelterBot.repository;

import jd5.ShelterBot.shelterBot.model.BotResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий, в котором хранятся ответы бота
 */
@Repository
public interface BotResponseRepository extends JpaRepository <BotResponse,Long> {
    BotResponse findBotResponseByKeyMessage (String keyMessage);
}