package com.wagner.commentscontrolproj.dto;

import com.wagner.commentscontrolproj.projection.TopUsersVew;
import lombok.Data;

@Data
public class TopUsersDTO {

    private Long id;
    private String userName;
    private Integer countPosts;
    private Integer countComments;
    private Integer IdLastPost;

    public static TopUsersDTO fromProj(TopUsersVew usersView){
        TopUsersDTO topUsersDTO = new TopUsersDTO();
        topUsersDTO.setId(usersView.getUserId());
        topUsersDTO.setUserName(usersView.getUserName());
        topUsersDTO.setCountComments(usersView.getCountComments());
        topUsersDTO.setCountPosts(usersView.getCountPosts());
        topUsersDTO.setIdLastPost(usersView.getIdLastPost());
        return topUsersDTO;
    }
}
