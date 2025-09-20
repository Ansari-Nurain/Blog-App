package com.blog_app_apis.services;

import java.util.List;

import com.blog_app_apis.payloads.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	CategoryDTO getCategoryById(Integer categoryId);
	
	List<CategoryDTO> getAllCategory();

}
