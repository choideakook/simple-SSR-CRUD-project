package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class Item {

    private Long id;
    private String itemName;

    // Integer 를 사용하면 값이 null 이 되도 예외가 발생하지 않는다.
    private Integer price;
    private Integer quantity;

    //-- 생성 method --//

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
