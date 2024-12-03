package com.social.service;
import com.social.models.Post;

import java.util.List;

public interface PostService {

    Post createNewPost(Post post, Integer userId) throws Exception;

    String deletePost(Integer portId, Integer userId) throws Exception;

    List<Post> findByUserId(Integer userId);

    Post findPostById(Integer postId) throws Exception;

    List<Post> findAllPost();


    Post savedPost(Integer postId, Integer userId) throws Exception;

    Post likePost(Integer postId, Integer userId) throws Exception;


}
