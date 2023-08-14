package com.pragyakantjha.blogging.services.impl;

import com.pragyakantjha.blogging.entities.Category;
import com.pragyakantjha.blogging.entities.Post;
import com.pragyakantjha.blogging.entities.User;
import com.pragyakantjha.blogging.exceptions.ResourceNotFoundException;
import com.pragyakantjha.blogging.payload.PostDto;
import com.pragyakantjha.blogging.repositories.CategoryRepository;
import com.pragyakantjha.blogging.repositories.PostRepository;
import com.pragyakantjha.blogging.repositories.UserRepository;
import com.pragyakantjha.blogging.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto newPostDto, Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
        post.setTitle(newPostDto.getTitle());
        post.setContent(newPostDto.getContent());
        Post updatedPost = this.postRepository.save(post);
        PostDto updatedPostDto = modelMapper.map(updatedPost, PostDto.class);
        return updatedPostDto;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Post> pages = this.postRepository.findAll(page);
        List<Post> posts = pages.getContent();
        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> posts = this.postRepository.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
        List<Post> posts = this.postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
