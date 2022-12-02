package com.content_manager_service.service.impl;


import com.content_manager_service.dao.DiscussionForumDao;
import com.content_manager_service.service.DiscussionForumManagerService;
import com.model.DiscussionForumVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiscussionForumManagerServiceImpl implements DiscussionForumManagerService {

    private final DiscussionForumDao discussionForumDao;

    @Override
    public ResponseEntity createDiscussionForum(DiscussionForumVO discussionForumVO) {
        int result = discussionForumDao.createDiscussionForum(discussionForumVO);
        if(result > 0){
            return APIResponse.resultSuccess("Discussion forum successfully created. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity getAllDiscussionForums() {
        List<DiscussionForumVO> discussionForums = discussionForumDao.getAllDiscussionForums();
        if(discussionForums.isEmpty()){
          return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("DISCUSSION_FORUMS", discussionForums);
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
    public ResponseEntity deleteDiscussionForum(Long forumId) {
        int result = discussionForumDao.deleteDiscussionForum(forumId);
        if(result > 0){
            return APIResponse.resultSuccess("Discussion forum successfully deleted.");
        }
        return APIResponse.resultFail();
    }
}
