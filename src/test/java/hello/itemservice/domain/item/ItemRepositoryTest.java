package hello.itemservice.domain.item;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void sava() {
        Item item = new Item("itemA", 10000, 10);

        Item savaItem = itemRepository.sava(item);
        Item findItem = itemRepository.findOne(item.getId());

        assertThat(findItem).isEqualTo(savaItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemA", 20000, 20);

        Item savaItem1 = itemRepository.sava(item1);
        Item savaItem2 = itemRepository.sava(item2);

        List<Item> findAll = itemRepository.findAll();

        assertThat(findAll.size()).isEqualTo(2);
        assertThat(findAll).contains(savaItem1, savaItem2);
    }

    @Test
    void update() {
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.sava(item);
        Long itemId = savedItem.getId();

        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(itemId, updateParam);

        Item findItem = itemRepository.findOne(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}