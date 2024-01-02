package com.ra.service.category;

import com.ra.model.dto.reponse.CategoryResponse;
import com.ra.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    // phaan trang tra ve DTO
    Page<CategoryResponse> getAll(Pageable pageable);

    Category saveOrUpdate(Category category);
    Category findById(Long id);
    void delete(Long id);
    Page<Category> getPaginate(Pageable pageable);
    Page<Category> getAllPaginate(Pageable pageable);

    Page<CategoryResponse> searchByName(Pageable pageable,String name);

}
