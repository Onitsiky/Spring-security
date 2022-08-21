package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.Post;
import com.example.springsecuritydemo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;

    public Optional<Post> getById(Long postId){
        return repository.findById(postId);
    }

    public List<Post> getAll(){
        return repository.findAll();
    }

    @Transactional
    public List<Post> saveAll(List<Post> posts){
        return repository.saveAll(posts);
    }

    public List<Post> getPostsByUserId(Long userId){
        return repository.findByUserId(userId);
    }
}
