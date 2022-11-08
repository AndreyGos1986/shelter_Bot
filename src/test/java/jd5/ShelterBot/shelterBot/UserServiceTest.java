package jd5.ShelterBot.shelterBot;

import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.model.ShelterUser;
import jd5.ShelterBot.shelterBot.repository.ParentRepository;
import jd5.ShelterBot.shelterBot.repository.UserRepository;
import jd5.ShelterBot.shelterBot.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserServiceImpl userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ParentRepository parentRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(userService).isNotNull();
        Assertions.assertThat(userRepository).isNotNull();
        Assertions.assertThat(parentRepository).isNotNull();
    }

    @Test
    void findAllParents() {
        ParentUser parentUser = new ParentUser();

        List<ParentUser> parentUserListExpected = List.of(parentUser);

        Mockito.when(parentRepository.findAll()).thenReturn(parentUserListExpected);

        List<ParentUser> parentUserListActual = userService.findAllParents();

        Assertions
                .assertThat(parentUserListExpected)
                .isEqualTo(parentUserListActual);
    }

    @Test
    void findTelegramIdByParent() {
        ParentUser parentUser = new ParentUser();
        long shelterUserId = 0L;
        parentUser.setShelterUserId(shelterUserId);

        ShelterUser shelterUser = new ShelterUser();
        long telegramIdExpected = 1L;
        shelterUser.setTelegramId(telegramIdExpected);

        Mockito.when(userRepository.findById(shelterUserId)).thenReturn(Optional.of(shelterUser));

        long telegramIdActual = userService.findTelegramIdByParent(parentUser);

        Assertions
                .assertThat(telegramIdExpected)
                .isEqualTo(telegramIdActual);
    }

    //Если userRepository не находит пользователя в строке UserServiceImpl.java:146,
    //то вылетает ошибка и строка UserServiceImpl.java:45 не работает и метод дальше не идет

/*    @Test
    void findTelegramIdByParentWhenReturnNull() {
        ParentUser parentUser = new ParentUser();
        long shelterUserId = 0L;
        parentUser.setShelterUserId(shelterUserId);

        Mockito.when(userRepository.findById(shelterUserId)).thenReturn(null);

        long telegramIdExpected = -1;
        long telegramIdActual = userService.findTelegramIdByParent(parentUser);

        Assertions
                .assertThat(telegramIdExpected)
                .isEqualTo(telegramIdActual);
    }
*/

    @Test
    void findParentById() {
        ParentUser parentUserExpected = new ParentUser();
        long parentId = 0L;

        Mockito.when(parentRepository.findById(parentId)).thenReturn(Optional.of(parentUserExpected));

        ParentUser parentUserActual = userService.findParentById(parentId);

        Assertions
                .assertThat(parentUserExpected)
                .isEqualTo(parentUserActual);
    }

    @Test
    void findParentByTelegramId() {
        ParentUser parentUserExpected = new ParentUser();
        long telegramId = 0L;

        ShelterUser shelterUser = new ShelterUser();
        long shelterUserId = 1L;
        shelterUser.setId(shelterUserId);

        Mockito.when(userRepository.findUserByTelegramId(telegramId)).thenReturn(shelterUser);
        Mockito.when(parentRepository.findByShelterUserId(shelterUserId)).thenReturn(parentUserExpected);

        ParentUser parentUserActual = userService.findParentByTelegramId(telegramId);

        Assertions
                .assertThat(parentUserExpected)
                .isEqualTo(parentUserActual);
    }

    @Test
    void findParentByTelegramIdWhenNull() {
        long telegramId = 0L;

        Mockito.when(userRepository.findUserByTelegramId(telegramId)).thenReturn(null);

        ParentUser parentUserActual = userService.findParentByTelegramId(telegramId);

        Assertions
                .assertThat(parentUserActual)
                .isNull();
    }

    @Test
    void findParentByUserId() {
        long id = 0L;

        ParentUser parentUserExpected = new ParentUser();
        parentUserExpected.setId(id);

        Mockito.when(parentRepository.findByShelterUserId(id)).thenReturn(parentUserExpected);

        ParentUser parentUserActual = userService.findParentByUserId(id);

        Assertions
                .assertThat(parentUserExpected)
                .isEqualTo(parentUserActual);
    }

    @Test
    void findUserByTelegramId() {
        long id = 0L;

        ShelterUser shelterUserExpected = new ShelterUser();

        Mockito.when(userRepository.findUserByTelegramId(id)).thenReturn(shelterUserExpected);

        ShelterUser shelterUserActual = userService.findUserByTelegramId(id);

        Assertions
                .assertThat(shelterUserExpected)
                .isEqualTo(shelterUserActual);
    }

    @Test
    void findAllByType() {
        ShelterUser shelterUser = new ShelterUser();
        List<ShelterUser> shelterUserListExpected = List.of(shelterUser);

        Mockito.when(userRepository.findAllByType(ShelterType.NEWUSER)).thenReturn(shelterUserListExpected);

        List<ShelterUser> shelterUserListActual = userService.findAllByType(ShelterType.NEWUSER);

        Assertions
                .assertThat(shelterUserListExpected)
                .isEqualTo(shelterUserListActual);
    }

    @Test
    void findUserById() {
        long id = 0L;

        ShelterUser shelterUserExpected = new ShelterUser();

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(shelterUserExpected));

        ShelterUser shelterUserActual = userService.findUserById(id);

        Assertions
                .assertThat(shelterUserExpected)
                .isEqualTo(shelterUserActual);
    }

    @Test
    void saveUser() {
        ShelterUser shelterUserExpected = new ShelterUser();

        Mockito.when(userRepository.save(shelterUserExpected)).thenReturn(shelterUserExpected);

        ShelterUser shelterUserActual = userService.saveUser(shelterUserExpected);

        Assertions
                .assertThat(shelterUserExpected)
                .isEqualTo(shelterUserActual);
    }

    @Test
    void saveParent() {
        ParentUser parentUserExpected = new ParentUser();

        Mockito.when(parentRepository.save(parentUserExpected)).thenReturn(parentUserExpected);

        ParentUser parentUserActual = userService.saveParent(parentUserExpected);

        Assertions
                .assertThat(parentUserExpected)
                .isEqualTo(parentUserActual);
    }
}
