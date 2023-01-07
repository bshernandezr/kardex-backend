package com.technical.kardex.domain.managers;

import com.technical.kardex.domain.exception.InvalidStockException;
import com.technical.kardex.domain.exception.ProductAlreadyCreatedException;
import com.technical.kardex.domain.exception.ProductNotFoundException;
import com.technical.kardex.domain.models.PageableEntity;
import com.technical.kardex.domain.models.StockUpdateRequest;
import com.technical.kardex.infrastructure.entities.Product;

public interface ProductManager {


    void addStockToProduct(StockUpdateRequest stockUpdateRequest) throws ProductNotFoundException, InvalidStockException;
    void updateTotalStockProduct(StockUpdateRequest stockUpdateRequest) throws ProductNotFoundException, InvalidStockException;
    void createProduct(Product product) throws ProductAlreadyCreatedException;
    Product getProductById(long productId) throws ProductNotFoundException;
    PageableEntity<Product> getListOfProducts(int currentPage, int hitsPerPage);
    void updateProduct(Product product) throws InvalidStockException;
    void deleteProduct(long productId) throws ProductNotFoundException;


}
