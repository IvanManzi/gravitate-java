package com.content_manager_service.dao;

import com.model.BlogVO;
import com.model.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogDao {

    int createBlog(BlogVO blogVO);

    List<Map> getAllBlogs(@Param("search") String search,@Param("topic") String topic);

    List<BlogVO> getUserBlogsByQuarter(@Param("userId") Long userId,@Param("quarter") Integer quarter);

    int updateBlog(BlogVO blogVO);

    int deleteBlog(Long topicId);
}
