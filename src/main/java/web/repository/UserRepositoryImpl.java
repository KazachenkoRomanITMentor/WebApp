package web.repository;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements  UserRepository{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> getAllUsers() {
        @SuppressWarnings("unchecked")
        List<User> users= null;
        users = entityManager.createQuery("FROM User", User.class).getResultList();
        users.forEach(user -> System.out.println(user.getName()));
        return users;
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public User readUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(Long id) {
        User user = readUser(id);
        if (user != null){
            entityManager.remove(user);
            entityManager.flush();
        }else {
            throw new NullPointerException("User not found");
        }
    }
}
