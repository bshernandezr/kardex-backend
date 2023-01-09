package com.technical.kardex.domain.handlers;

import com.technical.kardex.domain.dto.ProductInformation;
import com.technical.kardex.domain.exception.InvalidStockException;
import com.technical.kardex.domain.exception.ProductAlreadyCreatedException;
import com.technical.kardex.domain.exception.ProductNotFoundException;
import com.technical.kardex.domain.managers.ProductManager;
import com.technical.kardex.domain.models.PageableEntity;
import com.technical.kardex.domain.models.StockUpdateRequest;
import com.technical.kardex.infrastructure.entities.Product;
import com.technical.kardex.infrastructure.repository.ProductRepository;
import com.technical.kardex.infrastructure.repository.ProductStockHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductManagerHandler implements ProductManager {

    private final ProductRepository productRepository;

    private final ProductStockHistoryRepository productStockHistoryRepository;

    @Autowired
    public ProductManagerHandler(ProductRepository productRepository, ProductStockHistoryRepository productStockHistoryRepository) {
        this.productRepository = productRepository;
        this.productStockHistoryRepository = productStockHistoryRepository;
    }


    @Override
    public void addStockToProduct(StockUpdateRequest stockUpdateRequest) throws ProductNotFoundException, InvalidStockException {
        Optional<Product> optionalProduct = productRepository.findById(stockUpdateRequest.getProductId());
        if(!optionalProduct.isPresent())
            throw new ProductNotFoundException();
        Product product = optionalProduct.get();
        product.setStock(product.getStock() + stockUpdateRequest.getStock());
        validateStock(product);
        productRepository.save(product);
    }

    @Override
    public void updateTotalStockProduct(StockUpdateRequest stockUpdateRequest) throws ProductNotFoundException, InvalidStockException {
        Optional<Product> optionalProduct = productRepository.findById(stockUpdateRequest.getProductId());
        if(!optionalProduct.isPresent())
            throw new ProductNotFoundException();
        Product product = optionalProduct.get();
        product.setStock(stockUpdateRequest.getStock());
        validateStock(product);
        productRepository.save(product);
    }

    @Override
    public void createProduct(ProductInformation product) throws ProductAlreadyCreatedException {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if(optionalProduct.isPresent())
            throw new ProductAlreadyCreatedException();
        product.setStock(Math.abs(product.getStock()));
        productRepository.save(product.toEntity());
    }

    @Override
    public Product getProductById(long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent())
            throw new ProductNotFoundException();
        return optionalProduct.get();
    }

    @Override
    public PageableEntity<Product> getListOfProducts(int currentPage, int hitsPerPage) {
        PageableEntity<Product> productPageableEntity = new PageableEntity<>();
        productPageableEntity.setTotalHits(productRepository.count());
        productPageableEntity.setHitsPerPage(currentPage);
        productPageableEntity.setCurrentPage(hitsPerPage);
        productPageableEntity.setList(productRepository.findAll(PageRequest.of(currentPage, hitsPerPage)).toList());
        return productPageableEntity;
    }

    @Override
    public void updateProduct(ProductInformation product) throws InvalidStockException, ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if(!optionalProduct.isPresent())
            throw new ProductNotFoundException();
        validateStock(product.toEntity());
        productRepository.save(product.toEntity());
    }

    @Override
    public void deleteProduct(long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent())
            throw new ProductNotFoundException();
        productRepository.deleteById(productId);
    }

    private void validateStock(Product product) throws InvalidStockException {
        if(product.getStock() < 0)
            throw new InvalidStockException();
    }
}
