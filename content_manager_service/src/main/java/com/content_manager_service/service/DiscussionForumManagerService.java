package com.content_manager_service.service;


import com.model.DiscussionForumVO;
import org.springframework.http.ResponseEntity;

public interface DiscussionForumManagerService {
    ResponseEntity createDiscussionForum(DiscussionForumVO discussionForumVO);

    ResponseEntity getAllDiscussionForums();

    ResponseEntity getUserDiscussionForumsByQuarter(Long userId,Integer quarter);

    ResponseEntity updateDiscussionForum(DiscussionForumVO discussionForumVO);

    ResponseEntity deleteDiscussionForum(Long forumId);
}
