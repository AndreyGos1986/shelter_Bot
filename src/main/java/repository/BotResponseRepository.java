package repository;

import model.BotResponse;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Репозиторий, в котором хранятся ответы бота
 */
public interface BotResponseRepository extends JpaRepository <BotResponse,Long> {
    BotResponse findBotResponseByKeyMessage (String keyMessage);
}
