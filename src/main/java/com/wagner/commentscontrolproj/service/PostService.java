package com.wagner.commentscontrolproj.service;

import com.wagner.commentscontrolproj.dto.CommentDTO;
import com.wagner.commentscontrolproj.dto.PostDTO;
import com.wagner.commentscontrolproj.dto.TopUsersDTO;
import com.wagner.commentscontrolproj.projection.CommentProj;
import com.wagner.commentscontrolproj.projection.PostProj;
import com.wagner.commentscontrolproj.repository.CommentRepos;
import com.wagner.commentscontrolproj.repository.PostRepos;
import com.wagner.commentscontrolproj.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final CommentRepos commentRepos;
    private final PostRepos postRepos;
    private final UserRepos userRepos;

    @Autowired
    public PostService(CommentRepos commentRepos, PostRepos postRepos, UserRepos userRepos) {
        this.commentRepos = commentRepos;
        this.postRepos = postRepos;
        this.userRepos = userRepos;
    }

    public List<TopUsersDTO> getTopUserByMaxComments() {
        return commentRepos.getTopUserByMaxComments().stream().map(TopUsersDTO::fromProj).collect(Collectors.toList());
    }

    public List<PostDTO> getAllPostsWithComments() {
        return postRepos.findAllBy().stream()
                .map(PostDTO::from)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getAllPostsWithCommentsByPage(Integer pageNumber, Integer pageSize) {
        List<PostProj> postProj = postRepos.findProjBy(PageRequest.of(pageNumber - 1, pageSize));
        List<CommentProj> commentsProj = commentRepos.findByPostIdIn(postProj.stream()
                .map(PostProj::getId)
                .collect(Collectors.toList()));

        return postProj.stream()
                .map(PostDTO::fromPostProj)
                .peek(postDTO -> postDTO.setComments((commentsProj.stream()
                        .filter(comment -> comment.getPostId().equals(postDTO.getId())))
                        .map(CommentDTO::fromCommentProj).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<PostDTO> getPostWithCommentsEachContain(String body) {
        return postRepos.findAll((root, query, criteriaBuilder) -> {
                    root.fetch("comments", JoinType.LEFT).fetch("user", JoinType.LEFT);
                    query.distinct(true);
                    return criteriaBuilder.like(root.join("comments").get("body"), "%" + body + "%");
                })
                .stream()
                .map(PostDTO::from)
                .collect(Collectors.toList());

    }

}

