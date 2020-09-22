package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.controller.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;


    @Test
    @Transactional
    public void create(){
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail(("TestUser03@gmail.com"));
        user.setPhoneNumber("010-111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser03");

        User newUser = userRepository.save(user);
        System.out.println("newUser: " + newUser);
    }

    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser->{
                System.out.println("user: " + selectUser);
        });
    }

//    @Test
//    public User read(@RequestParam Long id){
//        Optional<User> user = userRepository.findById(id);
//        user.ifPresent(selectUser->{
//            System.out.println("user: " + selectUser);
//        });
//
//        return user.get();
//    }

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
