package com.social.service;


import com.social.exception.CommentException;
import com.social.exception.PostException;
import com.social.exception.UserException;
import com.social.models.Comment;


public interface CommentService {

    public Comment createComment(Comment comment, Integer postId, Integer userId) throws CommentException, UserException, PostException;

    public Comment findCommentById(Integer commentId) throws CommentException;

    public Comment likeComment(Integer CommentId, Integer userId) throws CommentException, UserException;

}
