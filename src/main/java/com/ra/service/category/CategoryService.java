package com.ra.service.category;

import com.ra.model.dto.reponse.CategoryResponse;
import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();
    Category saveOrUpdate(Category category);
    Category findById(Long id);
    void delete(Long id);
}
