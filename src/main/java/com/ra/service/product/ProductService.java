package com.ra.service.product;

import com.ra.model.dto.request.ProductRequest;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product saveOrUpdate(ProductRequest product);
    Product findById(Long id);
    void delete(Long id);
}
