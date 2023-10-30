package com.rekkei.academy.controller;

import com.rekkei.academy.entities.PostsEntity;
import com.rekkei.academy.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostsController {
    final
    PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<PostsEntity> getAll() {
        return postsService.getAll();
    }
    @PostMapping
    public PostsEntity createPost(@RequestBody PostsEntity post) {
        return postsService.createPost(post);
    }
    @GetMapping("/{postId}")
    public PostsEntity getPostById(@PathVariable Long postId) {
        return postsService.getPostById(postId);
    }
    @PutMapping
    public void updatePost(@RequestBody PostsEntity post) {
        postsService.updatePost(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postsService.deletePost(postId);
    }
}
