package com.content_manager_service.dao;

import com.model.DiscussionForumAnswerVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiscussionForumAnswerDao {

    int createDiscussionForumAnswer(DiscussionForumAnswerVO discussionForumAnswerVO);
}
