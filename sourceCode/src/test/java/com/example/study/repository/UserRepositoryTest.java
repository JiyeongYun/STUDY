package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
//    @Transactional
    public void create(){
        User user = new User();
        user.setAccount("TestUser06");
        user.setEmail(("TestUser06@gmail.com"));
        user.setPhoneNumber("010-111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser06");

        User newUser = userRepository.save(user);
        System.out.println("newUser: " + newUser);
    }

    @Test
    @Transactional
    public void read(){
//        Optional<User> user = userRepository.findById(2L);
        Optional<User> user = userRepository.findByAccountAndEmail("TestUser03", "TestUser03@gmail.com");
        user.ifPresent(selectUser->{

            selectUser.getOrderDetailList().stream().forEach(detail ->{
                Item item = detail.getItem();
                System.out.println(item);
            });

        });
    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        Assert.assertTrue(user.isPresent());            // true

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);
        Assert.assertFalse(deleteUser.isPresent());     // false
    }

//    @DeleteMapping("/api/user")
//    public void delete(@RequestParam Long id ){
//        Optional<User> user = userRepository.findById(id);
//        user.ifPresent(selectUser->{
//            userRepository.delete(selectUser);
//        });
//    }

}
