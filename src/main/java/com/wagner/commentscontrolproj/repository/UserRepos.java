package com.wagner.commentscontrolproj.repository;

import com.wagner.commentscontrolproj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos extends JpaRepository<User, Long> {
}
