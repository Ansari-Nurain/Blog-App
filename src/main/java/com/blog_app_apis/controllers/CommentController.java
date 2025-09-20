package com.blog_app_apis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_app_apis.payloads.ApiResponse;
import com.blog_app_apis.payloads.CommentDTO;
import com.blog_app_apis.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{id}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer id){
		
		CommentDTO createComment = commentService.createComment(commentDTO, id);
		
		return new ResponseEntity<CommentDTO>(createComment,HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
		
		commentService.deleteComment(id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment is deleted successfully !!", true),HttpStatus.OK);
	}
}
