package com.content_manager_service.dao;

import com.model.DiscussionForumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussionForumDao {

    int createDiscussionForum(DiscussionForumVO discussionForumVO);

    List<DiscussionForumVO> getAllDiscussionForums(@Param("search")String search,
                                                   @Param("title")String title,
                                                   @Param("tags")String tags);

    int updateDiscussionForum(DiscussionForumVO discussionForumVO);

    int deleteDiscussionForum(Long forumId);

    List<DiscussionForumVO> getUserDiscussionForumsByQuarter(@Param("userId") Long userId,@Param("quarter") Integer quarter);
}
