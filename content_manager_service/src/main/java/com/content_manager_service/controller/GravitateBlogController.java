package com.content_manager_service.controller;

import com.content_manager_service.form.CreateBlogCommentRequest;
import com.content_manager_service.form.CreateBlogRequest;
import com.content_manager_service.form.UpdateBlogRequest;
import com.content_manager_service.service.GravitateBlogManagerService;
import com.model.BlogReplyVO;
import com.model.BlogVO;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "/api/v1/content/blog",produces = "application/json")
@RequiredArgsConstructor
public class GravitateBlogController {

    private final GravitateBlogManagerService gravitateBlogManagerService;


    @PostMapping(value = "/create")
    public ResponseEntity createBlog(@Valid @RequestBody CreateBlogRequest createBlogRequest, HttpServletRequest request) throws IOException {
        BlogVO blogVO = new BlogVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        blogVO.setUserId(Long.valueOf(userId));
        blogVO.setTitle(createBlogRequest.title());
        blogVO.setTags(createBlogRequest.tags());
        blogVO.setProblemDescription(createBlogRequest.description());
        blogVO.setTopicThumbnail(createBlogRequest.thumbnail());
        return gravitateBlogManagerService.createBlog(blogVO);
    }

    @PostMapping(value = "/comment")
    public ResponseEntity createBlogReply(@Valid @RequestBody CreateBlogCommentRequest createBlogCommentRequest, HttpServletRequest request) throws IOException {
        BlogReplyVO blogReplyVO = new BlogReplyVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        blogReplyVO.setUserId(Long.valueOf(userId));
        blogReplyVO.setBlogId(createBlogCommentRequest.topicId());
        blogReplyVO.setComment(createBlogCommentRequest.comment());
        blogReplyVO.setParent(createBlogCommentRequest.parentBlogReplyId());
        return gravitateBlogManagerService.createBlogReply(blogReplyVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllBlogs(){
        return gravitateBlogManagerService.getAllBlogs();
    }

    @GetMapping(value = "/quarter")
    public ResponseEntity getGravitateUserBlogsByQuarter(@RequestParam("userId") Long userId,
                                                         @RequestParam("quarter") Integer quarter){
        return gravitateBlogManagerService.getUserBlogsByQuarter(userId,quarter);
    }

    @PutMapping(value = "/")
    public ResponseEntity updateBlog(@Valid @RequestBody UpdateBlogRequest updateBlogRequest, HttpServletRequest request) throws IOException {
        BlogVO blogVO = new BlogVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        blogVO.setBlogId(updateBlogRequest.topicId());
        blogVO.setUserId(Long.valueOf(userId));
        blogVO.setTitle(updateBlogRequest.title());
        blogVO.setTags(updateBlogRequest.tags());
        blogVO.setProblemDescription(updateBlogRequest.description());
        blogVO.setTopicThumbnail(updateBlogRequest.thumbnail());
        return gravitateBlogManagerService.updateBlog(blogVO);
    }

    @DeleteMapping("/")
    public ResponseEntity deleteBlog(@PathVariable("topicId") Long topicId){
        return gravitateBlogManagerService.deleteBlog(topicId);
    }

}
