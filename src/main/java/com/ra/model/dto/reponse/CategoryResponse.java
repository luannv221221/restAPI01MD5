package com.ra.model.dto.reponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CategoryResponse {
    private Long id;

    private String categoryName;
    private Boolean status;

    @JsonIgnore
    private List<Product> products;

    public CategoryResponse() {
    }

    public CategoryResponse(Long id, String categoryName, Boolean status) {
        this.id = id;
        this.categoryName = categoryName;
        this.status = status;
    }

    public CategoryResponse(Category category){
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.status = category.getStatus();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
