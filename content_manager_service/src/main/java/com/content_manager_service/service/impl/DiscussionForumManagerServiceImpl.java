package com.content_manager_service.service.impl;


import com.content_manager_service.dao.DiscussionForumAnswerDao;
import com.content_manager_service.dao.DiscussionForumDao;
import com.content_manager_service.dao.ForumAnswerUpVoteDao;
import com.content_manager_service.service.DiscussionForumManagerService;
import com.model.DiscussionForumAnswerVO;
import com.model.DiscussionForumVO;
import com.model.ForumAnswerUpVoteVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DiscussionForumManagerServiceImpl implements DiscussionForumManagerService {

    private final DiscussionForumDao discussionForumDao;

    private final DiscussionForumAnswerDao discussionForumAnswerDao;

    private final ForumAnswerUpVoteDao forumAnswerUpVoteDao;

    @Override
    public ResponseEntity createDiscussionForum(DiscussionForumVO discussionForumVO) {
        int result = discussionForumDao.createDiscussionForum(discussionForumVO);
        if(result > 0){
            return APIResponse.resultSuccess("Discussion forum successfully created. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity createDiscussionForumComment(DiscussionForumAnswerVO discussionForumAnswerVO) {
        int result = discussionForumAnswerDao.createDiscussionForumAnswer(discussionForumAnswerVO);
        if(result > 0){
            return APIResponse.resultSuccess("Forum answer successfully created. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity getAllDiscussionForums(Long forumId,String search,String title,String tags) {
        List<DiscussionForumVO> discussionForums = discussionForumDao.getAllDiscussionForums(forumId,search,title,tags);
        if(discussionForums.isEmpty()){
          return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("DISCUSSION_FORUMS", discussionForums);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity getUserAcceptedDiscussionSolutionsByQuarter(Long userId, Integer quarter, Integer year) {
        List<Map> discussionForums = discussionForumAnswerDao.getUserAcceptedDiscussionForumAnswers(userId,quarter,year);
        if(discussionForums.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("DISCUSSION_FORUMS",discussionForums);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity updateDiscussionForum(DiscussionForumVO discussionForumVO) {
        int result = discussionForumDao.updateDiscussionForum(discussionForumVO);
        if(result > 0){
            return APIResponse.resultSuccess("Discussion successfully updated. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity acceptDiscussionForumAnswer(Long answerId) {
        int result = discussionForumAnswerDao.acceptForumSolution(answerId);
        if(result > 0){
            return APIResponse.resultSuccess("Forum answer successfully approved.");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity upVoteForumAnswer(ForumAnswerUpVoteVO forumAnswerUpVoteVO) {
        int check = forumAnswerUpVoteDao.checkIfUserMadeForumAnswerUpVote(forumAnswerUpVoteVO);
        if(check == 1){
            return APIResponse.resultFail("You already up voted the answer. ");
        }
        int result = forumAnswerUpVoteDao.createForumAnswerUpVote(forumAnswerUpVoteVO);
        if (result > 0){
            return APIResponse.resultSuccess("Up vote successfully made. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity incrementForumViews(DiscussionForumVO discussionForumVO) {
        int result = discussionForumDao.incrementForumViewsCount(discussionForumVO);
        if(result > 0){
            return APIResponse.resultSuccess();
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity deleteDiscussionForum(Long forumId) {
        int result = discussionForumDao.deleteDiscussionForum(forumId);
        if(result > 0){
            return APIResponse.resultSuccess("Discussion forum successfully deleted.");
        }
        return APIResponse.resultFail();
    }

    @Override
    public boolean updateIsAwardedStatus(Long forumAnswerId, Long status) {
        int result = discussionForumAnswerDao.updateIsAwardedStatus(forumAnswerId,status);
        if(result > 0){
            return true;
        }
        return false;
    }
}
