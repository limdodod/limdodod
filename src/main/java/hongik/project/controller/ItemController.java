package hongik.project.controller;

import hongik.project.domain.Item;
import hongik.project.repository.ItemRepository;
import hongik.project.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller @RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping("item/new")
    public String createForm(Model model) {
        model.addAttribute("itemform", new ItemForm());
        return "item/createItemForm"; // item/createItemForm.html 페이지로 이동
    }

    @PostMapping(value="item/new")
    public String create(ItemForm form) {

            Item item = new Item();
            item.setName(form.getName());
            item.setPrice(form.getPrice());

            itemService.saveItem(item); // 아이템 저장
        return "redirect:/items"; // 등록 완료 후 아이템 목록 페이지로
    }

    // 아이템 목록 조회 메서드
    @GetMapping(value="/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems(); // 아이템 목록 조회
        model.addAttribute("items", items);
        return "item/itemList"; // itemList.html 페이지로 이동
    }


}
