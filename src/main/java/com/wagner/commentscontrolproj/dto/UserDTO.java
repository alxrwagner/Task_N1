package com.wagner.commentscontrolproj.dto;

import com.wagner.commentscontrolproj.model.User;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String username;

    public static UserDTO from (User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getName());
        return dto;
    }
}
