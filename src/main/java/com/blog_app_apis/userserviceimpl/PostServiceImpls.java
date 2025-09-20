package com.blog_app_apis.userserviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blog_app_apis.entities.Category;
import com.blog_app_apis.entities.Post;
import com.blog_app_apis.entities.User;
import com.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog_app_apis.payloads.PostDTO;
import com.blog_app_apis.payloads.PostResponse;
import com.blog_app_apis.repositries.CategoryRepository;
import com.blog_app_apis.repositries.PostRepositery;
import com.blog_app_apis.repositries.UserRepository;
import com.blog_app_apis.services.PostService;

@Service
public class PostServiceImpls implements PostService {
	
	@Autowired
	private PostRepositery postRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		
		Post post = this.mapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.mapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		
		Post savePost = this.postRepo.save(post);
		
		return this.mapper.map(savePost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		
		this.postRepo.delete(post);
	}

	@Override
	public PostDTO getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		
		return this.mapper.map(post, PostDTO.class);
	}

//	@Override
//	public List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize) {
//		
//		Pageable p = PageRequest.of(pageNumber, pageSize);
//
//		 Page<Post> pagePost = this.postRepo.findAll(p);
//		 List<Post> allPost = pagePost.getContent();
//		List<PostDTO> postDTO = allPost.stream().map((post)-> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());		
//		return postDTO;
//	}
	
	
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sorBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sorBy).ascending():Sort.by(sorBy).descending();
		
//		Sort sort = null;
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sorBy).ascending();
//		}else {
//			sort = Sort.by(sorBy).descending();
//		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		
//		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sorBy).descending());
		

		 Page<Post> pagePost = this.postRepo.findAll(p);
		 List<Post> allPost = pagePost.getContent();
		List<PostDTO> postDTO = allPost.stream().map((post)-> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());		
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDTO);
		postResponse.setPostNumber(pagePost.getNumber());
		postResponse.setPostSize(pagePost.getNumber());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDTO> getAllPostByCategoryId(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDTO> postDTO = posts.stream().map((post)-> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return postDTO;
	}

	@Override
	public List<PostDTO> getAllPostByUserId(Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDTO> postsDTO = posts.stream().map((post)-> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return postsDTO;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {

		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDTO> postDtos = posts.stream().map((post)-> this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}
