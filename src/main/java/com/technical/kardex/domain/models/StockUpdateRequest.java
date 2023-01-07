package com.technical.kardex.domain.models;


import lombok.Data;

@Data
public class StockUpdateRequest {
    private int stock;
    private long productId;
}
