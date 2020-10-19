package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  PaymentType {

    CARD(0, "카드", "카드 지불"),
    CHECK_CARD(1, "체크카드", "체크카드 지불"),
    BANK_TRANSFER(2, "계좌이체", "계좌이체 지불")
    ;

    private Integer id;
    private String title;
    private String description;
}
