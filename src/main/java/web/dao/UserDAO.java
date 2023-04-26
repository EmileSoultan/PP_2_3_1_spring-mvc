package web.dao;

import web.models.User;

import java.util.List;

public interface UserDAO {
    List<User> showAllUsers();
    User showUserById(long id);
    void save(User user);
    void update(long id, User user);
    void delete(long id);
}
