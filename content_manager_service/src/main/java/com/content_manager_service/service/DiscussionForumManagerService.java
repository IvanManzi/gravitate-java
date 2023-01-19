package com.content_manager_service.service;


import com.model.DiscussionForumAnswerVO;
import com.model.DiscussionForumVO;
import com.model.ForumAnswerUpVoteVO;
import org.springframework.http.ResponseEntity;

public interface DiscussionForumManagerService {
    ResponseEntity createDiscussionForum(DiscussionForumVO discussionForumVO);

    ResponseEntity createDiscussionForumComment(DiscussionForumAnswerVO discussionForumAnswerVO);

    ResponseEntity getAllDiscussionForums(String search,String title,String tags);

    ResponseEntity getUserAcceptedDiscussionSolutionsByQuarter(Long userId, Integer quarter, Integer year);

    ResponseEntity updateDiscussionForum(DiscussionForumVO discussionForumVO);

    ResponseEntity acceptDiscussionForumAnswer(Long answerId);

    ResponseEntity upVoteForumAnswer(ForumAnswerUpVoteVO forumAnswerUpVoteVO);

    ResponseEntity incrementForumViews(DiscussionForumVO discussionForumVO);

    ResponseEntity deleteDiscussionForum(Long forumId);
}
