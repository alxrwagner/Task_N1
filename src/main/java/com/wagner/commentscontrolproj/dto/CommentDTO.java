package com.wagner.commentscontrolproj.dto;

import com.wagner.commentscontrolproj.model.Comment;
import com.wagner.commentscontrolproj.projection.CommentProj;
import lombok.Data;

@Data
public class CommentDTO {

    private Long id;
    private String body;

    public static CommentDTO from(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }

    public static CommentDTO fromCommentProj (CommentProj commentProj) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentProj.getId());
        commentDTO.setBody(commentProj.getBody());
        return commentDTO;
    }
}
