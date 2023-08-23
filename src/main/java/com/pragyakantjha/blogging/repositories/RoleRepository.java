package com.pragyakantjha.blogging.repositories;

import com.pragyakantjha.blogging.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
