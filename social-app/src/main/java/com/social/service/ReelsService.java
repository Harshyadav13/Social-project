package com.social.service;

import com.social.exception.ReelsException;
import com.social.exception.UserException;
import com.social.models.Reels;
import com.social.models.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(Integer userId) throws ReelsException, UserException;
}
