package com.content_manager_service.dao;

import com.model.BlogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogDao {

    int createBlog(BlogVO blogVO);

    List<Map> getAllTeamMembersBlogs();

    int updateBlog(BlogVO blogVO);

    int deleteBlog(Long topicId);
}
