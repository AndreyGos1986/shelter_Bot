package service;

import model.ParentUser;
import model.User;
import model.UserType;

import java.util.List;

public interface UserService {

    ParentUser findParentByUserId(long id);

    User findUserByTelegramId(long id);

    List<User> findAllByType(UserType type);

    User findUserById(long id);

    User saveUser(User user);

    ParentUser saveParent(ParentUser parent);

    ParentUser registerAsParent(long userId, String phoneNumber);

    List<ParentUser> findAllParents();

    long findTelegramIdByParent(ParentUser parent);

    ParentUser findParentById(long parentId);

    ParentUser findParentByTelegramId(long telegramId);

}
