package hongik.project.repository;

import hongik.project.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save (User user){
        em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u order by u.name", User.class)
                .getResultList();
    }

    public List<User> findByName(String username) {
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", username)
                .getResultList();
    }
}
