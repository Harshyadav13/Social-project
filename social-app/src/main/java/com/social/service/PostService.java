package com.social.service;
import com.social.exception.CommentException;
import com.social.exception.PostException;
import com.social.exception.UserException;
import com.social.models.Post;

import java.util.List;

public interface PostService {

    Post createNewPost(Post post, Integer userId) throws PostException, UserException;

    String deletePost(Integer portId, Integer userId) throws PostException, UserException;

    List<Post> findByUserId(Integer userId);

    Post findPostById(Integer postId) throws PostException, CommentException;

    List<Post> findAllPost();


    Post savedPost(Integer postId, Integer userId) throws PostException, UserException;

    Post likePost(Integer postId, Integer userId) throws PostException, UserException;


}
