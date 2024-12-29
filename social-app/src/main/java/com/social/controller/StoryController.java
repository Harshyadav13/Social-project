package com.social.controller;

import com.social.exception.StoryException;
import com.social.exception.UserException;
import com.social.models.Story;
import com.social.models.User;
import com.social.service.StoryService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

            @Autowired
            private StoryService storyService;

            @Autowired
            private UserService userService;

            @PostMapping("/api/story")
            public Story createdStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {

                User reqUser = userService.findUserByJwt(jwt);

                Story createdStory = storyService.createStory(story, reqUser);

                return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUsersStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws StoryException, UserException {

        User reqUser = userService.findUserByJwt(jwt);

        List<Story> stories = storyService.findStoryByUserId(userId);

        return stories;
    }
}
