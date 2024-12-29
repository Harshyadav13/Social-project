package com.social.service;

import com.social.exception.StoryException;
import com.social.exception.UserException;
import com.social.models.Story;
import com.social.models.User;

import java.util.List;

public interface StoryService {

        public Story createStory(Story story, User user);

        public List<Story> findStoryByUserId(Integer userId) throws StoryException, UserException;



}
