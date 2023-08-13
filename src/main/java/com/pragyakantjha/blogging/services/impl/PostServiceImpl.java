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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        return null;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
