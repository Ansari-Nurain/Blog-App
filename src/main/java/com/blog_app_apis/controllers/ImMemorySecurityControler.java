package com.blog_app_apis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inmemory")
public class ImMemorySecurityControler {
	
	@GetMapping("/user/hello")
    public String userHello() {
        return "Hello User!";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin!";
    }

}
