package com.example.study.controller.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 여기서 다시 한 번 집고 넘어가자면, entity인 경우 보통 자바는 Camel case를 쓰지만 자동으로 DB snake case와 연결됨.
        // 따라서, order_detail이랑 연결 됨.
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    private Long userId;

    private Long itemId;

}
