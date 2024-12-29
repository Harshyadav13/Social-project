package com.social.controller;

import com.social.exception.ReelsException;
import com.social.exception.UserException;
import com.social.models.Reels;
import com.social.models.User;
import com.social.service.ReelsService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

            @Autowired
            private ReelsService reelsService;

            @Autowired
            private UserService userService;

            @PostMapping("/api/reels")
            public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {

                User reqUser = userService.findUserByJwt(jwt);

                Reels createdReels = reelsService.createReel(reel, reqUser);

                return createdReels;

            }
            @GetMapping("/api/reels")
             public List<Reels> findAllReels() {
             List<Reels> reels = reelsService.findAllReels();
             return reels;
            }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable Integer userId) throws ReelsException, UserException {
        List<Reels> reels = reelsService.findUsersReel(userId);
        return reels;
    }
}
