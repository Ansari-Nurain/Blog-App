package com.blog_app_apis.userserviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog_app_apis.entities.Comment;
import com.blog_app_apis.entities.Post;
import com.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog_app_apis.payloads.CommentDTO;
import com.blog_app_apis.repositries.CommentRepositiry;
import com.blog_app_apis.repositries.PostRepositery;
import com.blog_app_apis.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepositiry commentRepo;
	
	@Autowired
	private PostRepositery postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		
		comment.setPost(post);
		
		Comment save = this.commentRepo.save(comment);
		
		
		return modelMapper.map(save, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "comment id", commentId));
		commentRepo.delete(comment);
		
	}

}
