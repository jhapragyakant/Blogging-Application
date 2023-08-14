package com.pragyakantjha.blogging.controllers;

import com.pragyakantjha.blogging.payload.PostDto;
import com.pragyakantjha.blogging.services.PostService;
import com.pragyakantjha.blogging.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {


    @Autowired
    private PostService postService;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        PostDto createdPost = this.postService.createPost(postDto, userId,categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> postDtos = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @GetMapping("/get_all")
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize
    ){
        List<PostDto> postDtos = this.postService.getAllPosts(pageNumber, pageSize);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse(true,"Post deleted Successfully!!"), HttpStatus.OK);
    }
    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Integer postId){
        PostDto updatedPostDto = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }
}
