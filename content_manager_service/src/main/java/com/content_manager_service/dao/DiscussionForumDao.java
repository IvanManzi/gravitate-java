package com.content_manager_service.dao;

import com.model.DiscussionForumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussionForumDao {

    int createDiscussionForum(DiscussionForumVO discussionForumVO);

    List<DiscussionForumVO> getAllDiscussionForums();

    int updateDiscussionForum(DiscussionForumVO discussionForumVO);

    int deleteDiscussionForum(Long forumId);

    List<DiscussionForumVO> getUserDiscussionForumsByQuarter(@Param("userId") Long userId,@Param("quarter") Integer quarter);
}
