package hongik.project.repository;

import hongik.project.domain.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); //새로 등록
        } else {
            em.merge(item); //update
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();

    }

    // 아이템 삭제
    public void delete(Long id) {
        Item item = em.find(Item.class, id); // ID로 아이템 조회
        if (item != null) {
            em.remove(item); // 아이템 삭제
        }
    }
}