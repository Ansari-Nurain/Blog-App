package com.blog_app_apis.userserviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog_app_apis.entities.Category;
import com.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog_app_apis.payloads.CategoryDTO;
import com.blog_app_apis.repositries.CategoryRepository;
import com.blog_app_apis.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {

		Category cat = this.mapper.map(categoryDTO, Category.class);
		Category saveCat = this.categoryRepo.save(cat);
		
		return this.mapper.map(saveCat, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		cat.setCategoryTitle(categoryDTO.getCategoryTitle());
		cat.setCategoryDescription(categoryDTO.getCategoryDescription());
		
		Category updateCategory = this.categoryRepo.save(cat);
		
		return mapper.map(updateCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id",categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		
		return mapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {

		List<Category> allCategory = this.categoryRepo.findAll();
		List<CategoryDTO> categoryDtos = allCategory.stream().map((cat)-> this.mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		
		return categoryDtos;
	}


}
