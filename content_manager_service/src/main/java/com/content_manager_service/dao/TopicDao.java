package com.content_manager_service.dao;

import com.model.TopicVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TopicDao {

    int createTopic(TopicVO topicVO);

    List<Map> getAllTeamMembersTopics(@Param("managerId") Long managerId,@Param("search") String search);

    int updateTopic(TopicVO topicVO);

    int deleteTopic(Long topicId);
}
