package com.blog_app_apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_app_apis.payloads.ApiResponse;
import com.blog_app_apis.payloads.CategoryDTO;
import com.blog_app_apis.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
		CategoryDTO createCategory = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(createCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
		CategoryDTO updateCategory = this.categoryService.updateCategory(categoryDTO, categoryId);
		
		return new ResponseEntity<CategoryDTO>(updateCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted", true), HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
		
		CategoryDTO categoryById = this.categoryService.getCategoryById(categoryId);
		
		return new ResponseEntity<CategoryDTO>(categoryById, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){
		
		List<CategoryDTO> allCategory = this.categoryService.getAllCategory();
		
//		return new ResponseEntity<List<CategoryDTO>>(allCategory, HttpStatus.OK);
		return  ResponseEntity.ok(allCategory);

	}

	
}
