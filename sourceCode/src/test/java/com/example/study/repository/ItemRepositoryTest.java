package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
//    @Transactional
    public void create(){
        // 1. Create a object
        Item item = new Item();

        // 2. Set the info
        item.setName("휴대폰");
        item.setPrice(1000000);
        item.setContent("갤럭시5");

        // 3. Save
        Item newItem = itemRepository.save(item);

        // 4. Check
        Assert.assertNotNull(newItem);

        // 그냥 item이 아니라 save가 잘 된 것을 보기 위함이므로,
        // save가 잘 됐으면 Assert에
        // 정상적으로 repository를 통해 save가 작동하면 save된 아이템을 돌려주므로 Not null이 맞음.
    }

    @Test
    public void read(){
        Long id = 2L;

        // Optional: 있을 수도 있고, 없을 수도 있다!
        Optional<Item> item = itemRepository.findById(id);
        Assert.assertTrue(item.isPresent());

    }

}
