package hongik.project.service;

import hongik.project.domain.Item;
import hongik.project.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long product_id) {
        return itemRepository.findOne(product_id);
    }

    // 아이템 삭제
    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findOne(id);
        if (item == null) {
            throw new IllegalArgumentException("아이템을 찾을 수 없습니다.");
        }
        itemRepository.delete(id);
    }

}

