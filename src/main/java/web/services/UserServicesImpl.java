package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.User;
import web.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class UserServicesImpl implements web.services.UserServices {

    @Autowired
    private final UserRepository userRepository;


    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUsers();
    }

    @Override
    public User readUser(Long id) {
        return userRepository.readUser(id);
    }

    @Override
    public void createOrUpdateUser(User user) {
        if (user.getId()!=null){
            userRepository.updateUser(user);
        }else {
            userRepository.createUser(user);
        }

    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.deleteUser(id);
        } catch (NullPointerException exception){
            exception.printStackTrace();
        }
    }
    public void updateUser(User user){
        userRepository.updateUser(user);

    }
}
