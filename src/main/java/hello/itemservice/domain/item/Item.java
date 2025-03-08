package hello.itemservice.domain.item;

import lombok.Data;

@Data
public class Item {

    private Long id;            //상품고유번호
    private String itemName;    //상품이름
    private Integer price;     //상품가격
    private Integer quantity;   //상품수량 : 수량이 없을수도 있기 때문에 integer로 함

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
