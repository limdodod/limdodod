package hongik.project.controller;

import hongik.project.domain.Item;
import hongik.project.repository.ItemRepository;
import hongik.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // 아이템 목록 조회
    @GetMapping(value="/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems(); // 아이템 목록 조회
        model.addAttribute("items", items);
        return "item/itemList"; // itemList.html 페이지로 이동
    }


    // 아이템 삭제 처리
    @PostMapping("item/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        itemService.deleteItem(id);
        redirectAttributes.addFlashAttribute("message", "아이템이 성공적으로 삭제되었습니다.");
        return "redirect:/items";  // 아이템 목록으로 리다이렉트

    }
}
