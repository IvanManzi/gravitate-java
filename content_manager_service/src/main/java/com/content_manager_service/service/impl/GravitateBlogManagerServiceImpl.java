package com.content_manager_service.service.impl;

import com.content_manager_service.dao.BlogDao;
import com.content_manager_service.dao.BlogReplyDao;
import com.content_manager_service.service.GravitateBlogManagerService;
import com.model.BlogReplyVO;
import com.model.BlogVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GravitateBlogManagerServiceImpl implements GravitateBlogManagerService {

    private final BlogDao blogDao;

    private final BlogReplyDao blogReplyDao;

    @Override
    public ResponseEntity createBlog(BlogVO blogVO) {
        int result = blogDao.createBlog(blogVO);
        if(result > 0){
            return APIResponse.resultSuccess("Topic successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity createBlogReply(BlogReplyVO blogReplyVO) {
        int result = blogReplyDao.createBlogReply(blogReplyVO);
        if(result > 0){
            return APIResponse.resultSuccess("Topic reply successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllTeamBlogs() {
        List<Map> teamTopics = blogDao.getAllTeamMembersBlogs();
        if(teamTopics.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("TEAM_TOPICS",teamTopics);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity getUserBlogsByQuarter(Long userId, Integer quarter) {
        List<BlogVO> userBlogs = blogDao.getUserBlogsByQuarter(userId,quarter);
        if(userBlogs.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_BLOGS",userBlogs);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity updateBlog(BlogVO blogVO) {
        int result = blogDao.updateBlog(blogVO);
        if(result > 0){
            return APIResponse.resultSuccess("Topic successfully updated. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity deleteBlog(Long topicId) {
        int result  = blogDao.deleteBlog(topicId);
        if(result > 0){
            return APIResponse.resultSuccess("Topic successfully deleted.");
        }else{
            return APIResponse.resultFail();
        }
    }
}
