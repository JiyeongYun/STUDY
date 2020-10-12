package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  PaymentType {

    CASH(0, "현금", "현금 지불"),
    CARD(1, "카드", "카드 지불")
    ;

    private Integer id;
    private String title;
    private String description;
}
