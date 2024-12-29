package com.social.controller;

import com.social.exception.CommentException;
import com.social.exception.PostException;
import com.social.exception.UserException;
import com.social.models.Post;
import com.social.models.User;
import com.social.response.ApiResponse;
import com.social.service.PostService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/api/posts")

    public ResponseEntity<Post> createPost(@RequestHeader("Authorization")String jwt,
                                           @RequestBody Post post) throws PostException, UserException {

        User reqUser = userService.findUserByJwt(jwt);
        Post createPost = postService.createNewPost(post, reqUser.getId());
        return new ResponseEntity<>(createPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(
            @PathVariable Integer postId,
            @RequestHeader("Authorization")String jwt) throws PostException, UserException {

        User reqUser = userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId, reqUser.getId());
        ApiResponse res=new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);

    }

    //This method retrieves a single post by its postId.
    //Returns the post along with a status of 202 ACCEPTED, which usually means the request was successfully processed and accepted, but it’s more commonly used for asynchronous processing. For simple retrieval, HttpStatus.OK might be more typical.

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws PostException, CommentException {

        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);

    }

    //This method is used to retrieve all posts created by a specific user based on their userId

    @GetMapping("/api/posts/user/{userId}")
    public ResponseEntity<List<Post>>findUsersPost(@PathVariable Integer userId) throws PostException {

        List<Post> posts = (List<Post>) (List<Post>) postService.findByUserId(userId);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }


    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>>findAllPost() throws PostException {

        List<Post> posts = postService.findAllPost();

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }


    @PutMapping("/api/posts/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,
                                                 @RequestHeader("Authorization")String jwt) throws PostException, UserException {

        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(
            @PathVariable Integer postId,
            @RequestHeader("Authorization")String jwt) throws PostException, UserException {

        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, reqUser.getId());

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

}
