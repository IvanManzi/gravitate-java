package com.content_manager_service.dao;

import com.model.TopicReplyVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicReplyDao {

    int createTopicReply(TopicReplyVO topicReplyVO);
}
