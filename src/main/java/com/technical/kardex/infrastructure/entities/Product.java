package com.technical.kardex.infrastructure.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Product {

    @Id
    private long id;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private String category;

    @Column
    private int stock;

    @Column
    private Date creationDate;

    @Column
    private Date lastUpdate;

}
