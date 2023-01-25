package com.content_manager_service.service.impl;

import com.content_manager_service.dao.BlogDao;
import com.content_manager_service.dao.BlogReplyDao;
import com.content_manager_service.service.GravitateBlogManagerService;
import com.model.BlogReplyVO;
import com.model.BlogVO;
import com.model.UserVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            return APIResponse.resultSuccess("Blog successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity createBlogReply(BlogReplyVO blogReplyVO) {
        int result = blogReplyDao.createBlogReply(blogReplyVO);
        if(result > 0){
            return APIResponse.resultSuccess("Blog reply successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllBlogs(String search, String topic,Long blogId) {
        List<Map> teamBlogs = blogDao.getAllBlogs(search,topic,blogId);
        if(teamBlogs.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        for(Map blog : teamBlogs){
            blog.put("comments",buildCommentTree((Long) blog.get("blog_id"),(Long) blog.get("parent")));
        }

        Map<String,Object> data = new HashMap<>();
        data.put("ALL_BLOGS",teamBlogs);
        return APIResponse.resultSuccess(data);
    }

    private List<Map> buildCommentTree(Long blogId, Long parent) {
        List<Map> parentComments = blogDao.getBlogComments(blogId, parent);
        List<Map> childComments;
        if(parentComments.isEmpty()){
            return parentComments;
        }
        for(Map parentComment : parentComments){
            //retrieve child comments
            childComments = blogDao.getBlogComments(blogId,(Long) parentComment.get("blog_reply_id"));
            if(childComments.isEmpty()){
                continue;
            }else{
                for(Map childComment : childComments){
                    if(childComment.get("parent") != null && !childComment.get("parent").equals(parentComment.get("blog_reply_id"))){
                        childComment.put("comments",buildCommentTree(blogId,(Long)childComment.get("parent")));
                    }
                }
                parentComment.put("comments",childComments);
            }
        }
        return parentComments;
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
            return APIResponse.resultSuccess("Blog successfully updated. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity deleteBlog(Long topicId) {
        int result  = blogDao.deleteBlog(topicId);
        if(result > 0){
            return APIResponse.resultSuccess("Blog successfully deleted.");
        }else{
            return APIResponse.resultFail();
        }
    }
}
