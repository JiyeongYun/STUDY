package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.controller.model.entity.Item;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        // 1. Create a object
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("삼성 노트북");

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);

        // 그냥 item이 아니라 save가 잘 된 것을 보기 위함이므로,
        // save가 잘 됐으면 Assert에
        // 정상적으로 repository를 통해 save가 작동하면 save된 아이템을 돌려주므로 Not null이 맞음.



    }

    @Test
    public void read(){

    }

}
