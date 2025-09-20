package com.blog_app_apis.services;

import java.util.List;

import com.blog_app_apis.entities.Post;
import com.blog_app_apis.payloads.PostDTO;
import com.blog_app_apis.payloads.PostResponse;

public interface PostService {
	
//	Create
	
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
//	Update
	
	PostDTO updatePost(PostDTO postDTO,Integer postId);
	
//	Delete
	
	void deletePost(Integer postId);
	
//	Get single post
	
	PostDTO getPostById(Integer postId);
	
//	Get All posts
	
//	List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);

	
//	Get all post by category
	
	List<PostDTO> getAllPostByCategoryId(Integer categoryId);
	
//	get all post by user
	
	List<PostDTO> getAllPostByUserId(Integer userId);
	
//	Search posts

	List<PostDTO> searchPosts(String keyword);
}
