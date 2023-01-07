package com.technical.kardex.application.controllers;

import com.technical.kardex.application.models.GenericResponse;
import com.technical.kardex.domain.managers.ProductManager;
import com.technical.kardex.domain.models.PageableEntity;
import com.technical.kardex.infrastructure.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductManager productManager;

    @Autowired
    public ProductController(ProductManager productManager) {
        this.productManager = productManager;
    }


    @GetMapping
    public ResponseEntity<GenericResponse<PageableEntity<Product>>> getProductEntityList(int page,  int hitsPerPage) {
        return new ResponseEntity<>(new GenericResponse<>(this.productManager.getListOfProducts(page, hitsPerPage), HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()), HttpStatus.OK);
    }
}
