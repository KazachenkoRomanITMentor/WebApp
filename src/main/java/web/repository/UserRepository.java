package web.repository;

import web.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    void createUser(User user);
    User readUser(Long id);

    void updateUser(User user);

    void  deleteUser(Long id);

}
