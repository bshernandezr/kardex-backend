package com.technical.kardex.infrastructure.entities;

import com.technical.kardex.infrastructure.enums.StockOperation;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class ProductStockHistory {

    @Id
    private long id;

    @Column
    private Date creationDate;

    @Column
    private StockOperation stockOperation;

    @ManyToOne
    private Product product;

    @Column
    private int quantity;

}
