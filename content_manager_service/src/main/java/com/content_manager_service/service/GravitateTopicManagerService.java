package com.content_manager_service.service;


import com.model.TopicReplyVO;
import com.model.TopicVO;
import org.springframework.http.ResponseEntity;

public interface GravitateTopicManagerService {

    ResponseEntity createTopic(TopicVO topicVO);

    ResponseEntity createTopicReply(TopicReplyVO topicReplyVO);

    ResponseEntity getAllTeamTopics(Long managerId, String search);

    ResponseEntity updateTopic(TopicVO topicVO);

    ResponseEntity deleteTopic(Long topicId);
}
