package com.blog_app_apis.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

	private List<PostDTO> content;
	private int postNumber;
	private int postSize;
	private long totalElement;
	private int totalPage;
	private Boolean lastPage;
}
