package jd5.ShelterBot.shelterBot.repository;

import jd5.ShelterBot.shelterBot.model.ShelterUser;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <ShelterUser,Long> {

    ShelterUser findUserByTelegramId(long telegramId);

    List<ShelterUser> findAllByType(ShelterType type);

}
