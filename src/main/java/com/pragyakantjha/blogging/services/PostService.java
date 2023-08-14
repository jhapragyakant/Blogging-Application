package com.pragyakantjha.blogging.services;

import com.pragyakantjha.blogging.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto newPostDto, Integer postId);
    void deletePost(Integer postId);
    List<PostDto> getAllPosts();
    PostDto getPostById(Integer postId);
    //get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);
    //get all post by user
    List<PostDto> getPostByUser(Integer userId);
    //search posts
    List<PostDto> searchPosts(String keyword);
}
