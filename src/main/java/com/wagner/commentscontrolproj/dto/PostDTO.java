package com.wagner.commentscontrolproj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wagner.commentscontrolproj.model.Post;
import com.wagner.commentscontrolproj.projection.PostProj;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDTO user;

    public static PostDTO from(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        List<CommentDTO> comments = post.getComments().stream()
                .map(CommentDTO::from)
                .collect(Collectors.toList());
        postDTO.setComments(comments);
        postDTO.setUser(UserDTO.from(post.getUser()));
        return postDTO;
    }

    public static PostDTO fromPostProj (PostProj postProj) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(postProj.getId());
        postDTO.setTitle(postProj.getTitle());
        postDTO.setBody(postProj.getBody());
        return postDTO;
    }
}
