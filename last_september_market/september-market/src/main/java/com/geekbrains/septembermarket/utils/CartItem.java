package com.geekbrains.septembermarket.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long id;
    private String title;
    private int price;
    private int amount;

}
