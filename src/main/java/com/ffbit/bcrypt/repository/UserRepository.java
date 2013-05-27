package com.ffbit.bcrypt.repository;

import com.ffbit.bcrypt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

}
