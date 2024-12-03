package com.social.controller;

import com.social.models.Post;
import com.social.response.ApiResponse;
import com.social.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception {


        Post createPost = postService.createNewPost(post, userId);
        return new ResponseEntity<>(createPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {

        String message = postService.deletePost(postId, userId);
        ApiResponse res=new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);

    }

    //This method retrieves a single post by its postId.
    //Returns the post along with a status of 202 ACCEPTED, which usually means the request was successfully processed and accepted, but it’s more commonly used for asynchronous processing. For simple retrieval, HttpStatus.OK might be more typical.

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId)throws Exception {

        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);

    }

    //This method is used to retrieve all posts created by a specific user based on their userId

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>>findUsersPost(@PathVariable Integer userId) throws Exception {

        List<Post> posts = (List<Post>) (List<Post>) postService.findPostById(userId);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<Post>>findAllPost() throws Exception {

        List<Post> posts = postService.findAllPost();

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }


    @PutMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {

        Post post = postService.savedPost(postId, userId);

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {

        Post post = postService.likePost(postId, userId);

        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

}
