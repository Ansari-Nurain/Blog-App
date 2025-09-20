package com.blog_app_apis.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog_app_apis.entities.Category;
import com.blog_app_apis.entities.Post;
import com.blog_app_apis.entities.User;

public interface PostRepositery extends JpaRepository<Post, Integer> {
	
//	Custome Finder Method
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);

}
