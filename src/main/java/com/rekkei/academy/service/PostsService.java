package com.rekkei.academy.service;

import com.rekkei.academy.entities.PostsEntity;
import com.rekkei.academy.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {
    @Autowired
    PostsRepository postsRepository;

    public List<PostsEntity> getAll() {
        List<PostsEntity> listPost = postsRepository.findAll();
        if (listPost !=null && listPost.size() > 0) {
            return listPost;
        }
        return null;
    }

    public PostsEntity createPost(PostsEntity post) {
        return postsRepository.save(post);
    }
    public PostsEntity getPostById(Long postId) {
        return postsRepository.findById(postId).orElse(null);
    }
    public void updatePost(PostsEntity post) {
        postsRepository.save(post);
    }
    public void deletePost(Long postId) {
        postsRepository.deleteById(postId);
    }
}
