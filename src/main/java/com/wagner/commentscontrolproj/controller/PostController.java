package com.wagner.commentscontrolproj.controller;

import com.wagner.commentscontrolproj.dto.PostDTO;
import com.wagner.commentscontrolproj.dto.TopUsersDTO;
import com.wagner.commentscontrolproj.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/users/top10")
    public ResponseEntity<List<TopUsersDTO>> getTopUsers() {
        return ResponseEntity.ok(postService.getTopUserByMaxComments());
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllByPage(@RequestParam(required = false) Integer pageNumber,
                                                      @RequestParam(required = false) Integer size){
        if (pageNumber == null && size == null){
            return ResponseEntity.ok(postService.getAllPostsWithComments());
        } else if (size == null) {
            size = 3;
        } else if (pageNumber == null) {
            pageNumber = 0;
        }
        return ResponseEntity.ok(postService.getAllPostsWithCommentsByPage(pageNumber, size));
    }

    @GetMapping("/comments/contain")
    public ResponseEntity<List<PostDTO>> getPostWithCommentsEachContain(@RequestParam String body){
        return ResponseEntity.ok(postService.getPostWithCommentsEachContain(body));
    }
}
