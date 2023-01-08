package com.technical.kardex.domain.models;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StockUpdateRequest {
    @Schema(name = "stock", description = "Numero puede ser positivo o negativo para sumarle al stock del producto", defaultValue = "", example = "")
    private int stock;


    @Schema(name = "productId", description = "Id del producto", defaultValue = "", example = "")
    private long productId;
}
