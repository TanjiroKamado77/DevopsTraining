package com.devops.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class PostMessageController {
	
	@GetMapping("/myposts/{uid}")
	public PostMessage get(@PathVariable("uid")String uid) {
		
		String url = "https://jsonplaceholder.typicode.com/posts/" + uid;
		
		RestClient rc = RestClient.builder().build();
		
		PostMessage result = rc.get().uri(url).retrieve().body(PostMessage.class);
		
		System.out.println(result.toString());
		return result;
	}
}
