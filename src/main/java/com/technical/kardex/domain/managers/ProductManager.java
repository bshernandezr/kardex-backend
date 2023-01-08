package com.technical.kardex.domain.managers;

import com.technical.kardex.domain.dto.ProductInformation;
import com.technical.kardex.domain.exception.InvalidStockException;
import com.technical.kardex.domain.exception.ProductAlreadyCreatedException;
import com.technical.kardex.domain.exception.ProductNotFoundException;
import com.technical.kardex.domain.models.PageableEntity;
import com.technical.kardex.domain.models.StockUpdateRequest;
import com.technical.kardex.infrastructure.entities.Product;

public interface ProductManager {


    void addStockToProduct(StockUpdateRequest stockUpdateRequest) throws ProductNotFoundException, InvalidStockException;
    void updateTotalStockProduct(StockUpdateRequest stockUpdateRequest) throws ProductNotFoundException, InvalidStockException;
    void createProduct(ProductInformation product) throws ProductAlreadyCreatedException;
    Product getProductById(long productId) throws ProductNotFoundException;
    PageableEntity<Product> getListOfProducts(int currentPage, int hitsPerPage);

    void updateProduct(ProductInformation product) throws InvalidStockException, ProductNotFoundException;

    void deleteProduct(long productId) throws ProductNotFoundException;


}
