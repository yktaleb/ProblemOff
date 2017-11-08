package com.hw.service.category;

import com.hw.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    Set<Category> findAllMainCategories();

    Category addSubCategory(Long id, Long subId);

    Category createSubCategory(Long id, Category subCategory);

    Category setSuperCategory(Long id, Long superId);

    Category createSuperCategory(Long id, Category superCategory);

    Category alterToMain(Long id);
}
