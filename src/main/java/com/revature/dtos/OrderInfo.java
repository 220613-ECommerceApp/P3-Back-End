package com.revature.dtos;

import java.sql.Timestamp;

import com.revature.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    private int id;
    private Product product;
    private int quantity;
    private Timestamp timestamp;
}
