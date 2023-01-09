package com.technical.kardex.application.controllers;

import com.technical.kardex.application.models.GenericResponse;
import com.technical.kardex.domain.dto.ProductInformation;
import com.technical.kardex.domain.exception.InvalidStockException;
import com.technical.kardex.domain.exception.ProductAlreadyCreatedException;
import com.technical.kardex.domain.exception.ProductNotFoundException;
import com.technical.kardex.domain.managers.ProductManager;
import com.technical.kardex.domain.models.PageableEntity;
import com.technical.kardex.domain.models.StockUpdateRequest;
import com.technical.kardex.infrastructure.entities.Product;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductManager productManager;

    @Autowired
    public ProductController(ProductManager productManager) {
        this.productManager = productManager;
    }


    @ApiOperation(value = "getProductEntityList", notes = "Obtener los productos en el inventario de manera paginada.")
    @GetMapping
    public ResponseEntity<GenericResponse<PageableEntity<Product>>> getProductEntityList(@ApiParam(value = "Página", required = true, defaultValue = "0") int page,
                                                                                         @ApiParam(value = "Registros por página", required = true, defaultValue = "5") int hitsPerPage) {
        return new ResponseEntity<>(new GenericResponse<>(this.productManager.getListOfProducts(page, hitsPerPage), HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()), HttpStatus.OK);
    }

    @ApiOperation(value = "createProduct", notes = "Servicio para crear un producto nuevo")
    @PostMapping
    public ResponseEntity<GenericResponse<String>> createProduct(@ApiParam(value = "Información necesaria para crear un producto", required = true)
                                                                    @Valid @RequestBody ProductInformation product) throws ProductAlreadyCreatedException {
        this.productManager.createProduct(product);
        return new ResponseEntity<>(new GenericResponse<>("Producto creado exitosamente", HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()), HttpStatus.OK);
    }


    @ApiOperation(value = "getProductById", notes = "Obtener la información de un producto por medio del id")
    @GetMapping("{id}")
    public ResponseEntity<GenericResponse<Product>> getProductById(@Validated @ApiParam(value = "Id del producto", required = true, defaultValue = "0")@PathVariable(value = "id") long id) throws ProductNotFoundException {
        return new ResponseEntity<>(new GenericResponse<>(this.productManager.getProductById(id), HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GenericResponse<String>> updateProduct(@RequestBody ProductInformation productInformation) throws ProductNotFoundException, InvalidStockException {
        this.productManager.updateProduct(productInformation);
        return new ResponseEntity<>(new GenericResponse<>("Producto actualizado exitosamente", HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PutMapping("stock")
    public ResponseEntity<GenericResponse<String>> addStockProduct(@RequestBody StockUpdateRequest request) throws ProductNotFoundException, InvalidStockException {
        this.productManager.addStockToProduct(request);
        return new ResponseEntity<>(new GenericResponse<>("Producto actualizado exitosamente", HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()), HttpStatus.OK);
    }
}
