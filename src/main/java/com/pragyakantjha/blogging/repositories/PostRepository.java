package com.pragyakantjha.blogging.repositories;

import com.pragyakantjha.blogging.entities.Category;
import com.pragyakantjha.blogging.entities.Post;
import com.pragyakantjha.blogging.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
