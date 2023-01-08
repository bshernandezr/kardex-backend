package com.technical.kardex.domain.dto;

import com.technical.kardex.infrastructure.entities.Product;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;


@Data
public class ProductInformation {
    @NotNull
    @Positive
    private long id;

    @NotEmpty
    private String description;

    private double price;

    private String category;

    @Positive
    private int stock;

    public Product toEntity(){
        return new Product(
                this.id,
                this.description,
                this.price,
                this.category,
                this.stock,
                new Date(),
                new Date());
    }
}
