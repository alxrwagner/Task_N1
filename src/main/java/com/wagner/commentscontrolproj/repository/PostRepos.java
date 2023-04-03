package com.wagner.commentscontrolproj.repository;

import com.wagner.commentscontrolproj.model.Post;
import com.wagner.commentscontrolproj.projection.PostProj;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepos extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    List<PostProj> findProjBy(PageRequest pageRequest);
    @EntityGraph(attributePaths = {"comments", "user"})
    List<Post> findAllBy();
}
