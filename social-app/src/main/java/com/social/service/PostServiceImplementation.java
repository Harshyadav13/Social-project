package com.social.service;

import com.social.exception.PostException;
import com.social.exception.UserException;
import com.social.models.Post;
import com.social.models.User;
import com.social.repository.PostRepository;
import com.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Override
    public Post createNewPost(Post post, Integer userId) throws PostException, UserException {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
//        newPost.setCreatedAt(new LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws PostException, UserException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getUser().getId() != user.getId()) {
            throw new PostException("you can't delete another users post ");
        }

        postRepository.delete(post);

        return "post deleted successfully ";
    }


    @Override
    public List<Post> findByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws PostException {
        Optional<Post> opt = postRepository.findById(postId);
        if(opt.isEmpty()) {
            throw new PostException("post not found with id " + postId);
        }
        return opt.get();
    }

    @Override
    public List<Post> findAllPost() {

        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws PostException, UserException {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);

        }
        else user.getSavedPost().add(post);
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws PostException, UserException {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        }
        else {
            post.getLiked().add(user);
        }

        return postRepository.save(post);
    }
}
