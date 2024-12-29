package com.social.controller;

import com.social.exception.UserException;
import com.social.models.User;
import com.social.repository.UserRepository;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;



    @GetMapping("/api/users")
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws UserException {
        User user = userService.findUserById(id);
        return user;
    }


    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization")String jwt, @RequestBody User user) throws UserException {

        User reqUser = userService.findUserByJwt(jwt);

        User updatedUser = userService.updateUser(user, reqUser.getId());

        return updatedUser;
    }


    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization")String jwt, @PathVariable Integer userId2) throws UserException {

        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(), userId2);
        return user;
    }


    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {

        List<User> users = userService.searchUser(query);
        return users;
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization")String jwt) {

        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }


}
