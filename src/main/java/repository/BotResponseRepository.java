package repository;

import model.BotResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotResponseRepository extends JpaRepository <BotResponse,Long> {
    BotResponse findBotResponseByKeyMessage (String keyMessage);
}
