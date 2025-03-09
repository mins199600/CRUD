package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    //Integer int
    //Long long
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId , Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    //테스트용 데이터 추가(디비 인서트 안하고 추가 하는 방법)
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }

    @GetMapping("/addForm")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/addForm")
    public String addItemV1(@RequestParam("itemName") String itemName,
                       @RequestParam("quantity") Integer quantity ,
                       @RequestParam("price") int price,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item",item);

        return "basic/item";
    }

//    @PostMapping("/addForm")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {

        itemRepository.save(item);
//        model.addAttribute("item",item); 자동추가, 생략가능

        return "basic/item";
    }

//    @PostMapping("/addForm")
    public String addItemV3(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        return "basic/item";
    }
    @PostMapping("/addForm")
    public String addItemV4(Item item) {

        itemRepository.save(item);

        return "basic/item";
    }


}
