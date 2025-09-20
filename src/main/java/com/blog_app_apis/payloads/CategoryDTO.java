package com.blog_app_apis.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO {
	

	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4, message = "min size of title is 4 character")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10, message = "min size of description is 4 character")
	private String categoryDescription;

}
