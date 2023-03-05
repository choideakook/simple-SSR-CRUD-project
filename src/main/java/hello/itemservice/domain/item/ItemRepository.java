package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 싱글톤으로 운영하기 위해 static 을 붙여준다.
    // Spring 을 사용했기 때문에 사실 static 을 붙여주지 않아도 자동 싱글톤이 적용된다.
    private static final Map<Long, Item> store = new HashMap<>(); // 멀티 Thread 환경에서는 ConcurrentMap 을 사용해야 한다.
    private static long sequence = 0L;

    //-- sava --//
    public Item sava(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    //-- 단건 조회 --//
    public Item findOne(Long id) {
        return store.get(id);
    }

    //-- 전체 조회 --//
    public List<Item> findAll() {
        // 조회된 data 를 수정해도 store 에 반영되지 않게 하기 위해서
        // 새로운 List 를 생성해서 return 해준다.
        return new ArrayList<>(store.values());
    }

    //-- update --//
    public void update(Long id, Item updateParam) {
        // 프로젝트가 작기 때문에 setter 를 통해서 직접적으로 수정시켜준다.
        // 정석은 별도의 DTO 객체를 생성하는 것이 맞다.
        Item findItem = findOne(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    //-- test 용 로직 --//
    public void clearStore() {
        store.clear();
    }

}
