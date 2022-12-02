package com.content_manager_service.service;


import com.model.DiscussionForumVO;
import org.springframework.http.ResponseEntity;

public interface DiscussionForumManagerService {
    ResponseEntity createDiscussionForum(DiscussionForumVO discussionForumVO);

    ResponseEntity getAllDiscussionForums();

    ResponseEntity updateDiscussionForum(DiscussionForumVO discussionForumVO);

    ResponseEntity deleteDiscussionForum(Long forumId);
}
