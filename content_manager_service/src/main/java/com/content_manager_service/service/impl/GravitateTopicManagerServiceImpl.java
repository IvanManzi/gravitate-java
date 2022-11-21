package com.content_manager_service.service.impl;

import com.content_manager_service.dao.TopicDao;
import com.content_manager_service.dao.TopicReplyDao;
import com.content_manager_service.service.GravitateTopicManagerService;
import com.model.TopicReplyVO;
import com.model.TopicVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GravitateTopicManagerServiceImpl implements GravitateTopicManagerService {

    private final TopicDao topicDao;

    private final TopicReplyDao topicReplyDao;

    @Override
    public ResponseEntity createTopic(TopicVO topicVO) {
        int result = topicDao.createTopic(topicVO);
        if(result > 0){
            return APIResponse.resultSuccess("Topic successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity createTopicReply(TopicReplyVO topicReplyVO) {
        int result = topicReplyDao.createTopicReply(topicReplyVO);
        if(result > 0){
            return APIResponse.resultSuccess("Topic reply successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllTeamTopics(Long managerId, String search) {
        List<Map> teamTopics = topicDao.getAllTeamMembersTopics(managerId,search);
        if(teamTopics.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("TEAM_TOPICS",teamTopics);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateTopic(TopicVO topicVO) {
        int result = topicDao.updateTopic(topicVO);
        if(result > 0){
            return APIResponse.resultSuccess("Topic successfully updated. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity deleteTopic(Long topicId) {
        int result  = topicDao.deleteTopic(topicId);
        if(result > 0){
            return APIResponse.resultSuccess("Topic successfully deleted.");
        }else{
            return APIResponse.resultFail();
        }
    }
}
