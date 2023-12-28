package com.ra.service.category;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category saveOrUpdate(Category category);
    Category findById(Long id);
    void delete(Long id);
}
