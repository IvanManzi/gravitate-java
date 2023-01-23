package com.content_manager_service.controller;

import com.content_manager_service.form.CreateDiscussionForumAnswer;
import com.content_manager_service.form.CreateDiscussionForumRequest;
import com.content_manager_service.form.UpdateDiscussionForumRequest;
import com.content_manager_service.service.DiscussionForumManagerService;
import com.model.DiscussionForumAnswerVO;
import com.model.DiscussionForumVO;
import com.model.ForumAnswerUpVoteVO;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/content/forum")
@RequiredArgsConstructor
public class DiscussionForumManagerController {

    private final DiscussionForumManagerService discussionForumManagerService;


    @PostMapping(value = "/create")
    public ResponseEntity createDiscussionForum(@Valid @RequestBody CreateDiscussionForumRequest createDiscussionForumRequest,
                                                HttpServletRequest request) throws IOException {
        DiscussionForumVO discussionForumVO = new DiscussionForumVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        discussionForumVO.setUserId(Long.valueOf(userId));
        discussionForumVO.setTitle(createDiscussionForumRequest.title());
        discussionForumVO.setTags(createDiscussionForumRequest.tags());
        discussionForumVO.setDescription(createDiscussionForumRequest.body());
        return discussionForumManagerService.createDiscussionForum(discussionForumVO);
    }

    @PostMapping(value = "/comment/create")
    public ResponseEntity createDiscussionForumAnswer(@Valid @RequestBody CreateDiscussionForumAnswer createDiscussionForumAnswer,
                                                HttpServletRequest request) throws IOException {
        DiscussionForumAnswerVO discussionForumAnswerVO = new DiscussionForumAnswerVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        discussionForumAnswerVO.setUserId(Long.valueOf(userId));
        discussionForumAnswerVO.setForumId(createDiscussionForumAnswer.forumId());
        discussionForumAnswerVO.setParent(createDiscussionForumAnswer.parent());
        discussionForumAnswerVO.setComment(createDiscussionForumAnswer.comment());
        return discussionForumManagerService.createDiscussionForumComment(discussionForumAnswerVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllDiscussionForums(@RequestParam(value = "forumId",required = false) Long forumId,
                                                 @RequestParam(value = "search",required = false) String search,
                                                 @RequestParam(value = "title",required = false) String title,
                                                 @RequestParam(value = "tags",required = false) String tags) {
        return discussionForumManagerService.getAllDiscussionForums(forumId,search,title,tags);
    }

    @GetMapping(value = "/quarter")
    public ResponseEntity getUserAcceptedSolutionsByQuarter(@RequestParam("userId") Long userId,
                                                           @RequestParam("year") Integer year,
                                                           @RequestParam("quarter") Integer quarter){
        return discussionForumManagerService.getUserAcceptedDiscussionSolutionsByQuarter(userId,quarter,year);
    }

    @PutMapping(value = "/solution/{solutionId}/approve")
    public ResponseEntity approveDiscussionForumSolution(@PathVariable("solutionId") Long solutionId){
        return discussionForumManagerService.acceptDiscussionForumAnswer(solutionId);
    }

    @PutMapping(value = "/solution/{solutionId}/upVote")
    public ResponseEntity upVoteForumSolution(HttpServletRequest request,@PathVariable("solutionId") Long solutionId) throws IOException {
        ForumAnswerUpVoteVO forumAnswerUpVoteVO = new ForumAnswerUpVoteVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        forumAnswerUpVoteVO.setForumAnswerId(solutionId);
        forumAnswerUpVoteVO.setUserId(Long.valueOf(userId));

        return discussionForumManagerService.upVoteForumAnswer(forumAnswerUpVoteVO);
    }
    @PutMapping(value = "/{forumId}/views")
    public ResponseEntity incrementForumViews(@PathVariable("forumId") Long forumId){
        DiscussionForumVO discussionForumVO = new DiscussionForumVO();
        discussionForumVO.setDiscussionForumId(forumId);
        return discussionForumManagerService.incrementForumViews(discussionForumVO);
    }


    @PutMapping(value = "/")
    public ResponseEntity updateDiscussionForum(@Valid @RequestBody UpdateDiscussionForumRequest updateDiscussionForumRequest,
                                                HttpServletRequest request) throws IOException {
        DiscussionForumVO discussionForumVO = new DiscussionForumVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        discussionForumVO.setUserId(Long.valueOf(userId));
        discussionForumVO.setDiscussionForumId(updateDiscussionForumRequest.forumId());
        discussionForumVO.setTitle(updateDiscussionForumRequest.title());
        discussionForumVO.setTags(updateDiscussionForumRequest.tags());
        discussionForumVO.setDescription(updateDiscussionForumRequest.body());
        return discussionForumManagerService.updateDiscussionForum(discussionForumVO);
    }
    @DeleteMapping(value = "/{forumId}")
    public ResponseEntity deleteDiscussionForum(@PathVariable("forumId") Long forumId){
        return discussionForumManagerService.deleteDiscussionForum(forumId);
    }
}
