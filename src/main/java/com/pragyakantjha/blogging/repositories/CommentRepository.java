package com.pragyakantjha.blogging.repositories;

import com.pragyakantjha.blogging.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
