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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog_app_apis.config.AppConstant;
import com.blog_app_apis.payloads.ApiResponse;
import com.blog_app_apis.payloads.PostDTO;
import com.blog_app_apis.payloads.PostResponse;
import com.blog_app_apis.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);

		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer id) {

		PostDTO updatePost = this.postService.updatePost(postDTO, id);

		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{id}")
	public ApiResponse deletePost(@PathVariable Integer id) {

		this.postService.deletePost(id);

		return new ApiResponse("Post is successfully deleted !!", true);
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer id) {
		PostDTO postById = this.postService.getPostById(id);
		return new ResponseEntity<PostDTO>(postById, HttpStatus.OK);
	}

//	@GetMapping("/posts")
//	public ResponseEntity<List<PostDTO>> getAllPosts(
//			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
//			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
//		
//		List<PostDTO> allPost = this.postService.getAllPost(pageNumber,pageSize);
//		
//		return new ResponseEntity<List<PostDTO>>(allPost,HttpStatus.OK);
//	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			
//			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
//			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
//			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
//			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
			
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIE, required = false) String sortDir
			
			) {

		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getAllPostByUserId(@PathVariable Integer userId) {

		List<PostDTO> allPostByCategory = this.postService.getAllPostByCategoryId(userId);

		return new ResponseEntity<List<PostDTO>>(allPostByCategory, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getAllPostByCategoryId(@PathVariable Integer categoryId) {

		List<PostDTO> allPostByCategory = this.postService.getAllPostByCategoryId(categoryId);

		return new ResponseEntity<List<PostDTO>>(allPostByCategory, HttpStatus.OK);
	}

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByRTitle(@PathVariable("keywords") String keyword) {

		List<PostDTO> searchPosts = this.postService.searchPosts(keyword);

		return new ResponseEntity<List<PostDTO>>(searchPosts, HttpStatus.OK);
	}

}
