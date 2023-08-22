package com.pragyakantjha.blogging.repositories;

import com.pragyakantjha.blogging.entities.Category;
import com.pragyakantjha.blogging.entities.Post;
import com.pragyakantjha.blogging.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
