package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository repository;

    //-- 모든 item 리스트 조회 --//
    @GetMapping
    public String Items(Model model) {
        List<Item> items = repository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    //-- 특정 item 상세 내역 조회 --//
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = repository.findOne(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //-- 상품 등록 form --//
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //-- 상품 등록 V1 --//
//    @PostMapping("/add")
    public String addItemV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ) {
        Item item = new Item(itemName, price, quantity);
        Item sava = repository.sava(item);

        model.addAttribute("item", item);
        return "basic/item";
    }

    //-- 상품 등록 V2 --//
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        Item sava = repository.sava(item);
        return "basic/item";
    }

    //-- 상품 등록 V3 --//
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        Item sava = repository.sava(item);
        return "basic/item";
    }

    //-- 상품 등록 V4 --//
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        Item sava = repository.sava(item);
        return "basic/item";
    }

    //-- 상품 등록 V5 : redirect --//
//    @PostMapping("/add")
    public String addItemV5(Item item) {
        Item sava = repository.sava(item);
        return "redirect:/basic/items/" + item.getId();
    }

    //-- 상품 등록 V6 : RedirectAttributes --//
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savaItem = repository.sava(item);
        // RedirectAttributes 에 입력한 itemId 를 return 에서도 사용할 수 있게된다.
        redirectAttributes.addAttribute("itemId", savaItem.getId());
        // url 에 포함되지 않은 객체는 Query Parameter 형식으로 전달 된다.
        // ex) ?status=true
        // 만약 itemId 도 return 에 없었다면 status 와 함께 파라미터로 전달된다.
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    //-- 상품 수정 폼 --//
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = repository.findOne(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    //-- 상품 수정 --//
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        repository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    //-- Test 용 init data --//
    @PostConstruct
    public void init() {

        repository.sava(new Item("itemA", 10000, 10));
        repository.sava(new Item("itemB", 20000, 20));
    }



}
