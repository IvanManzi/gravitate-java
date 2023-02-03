package com.content_manager_service.dao;

import com.model.DiscussionForumAnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DiscussionForumAnswerDao {

    int createDiscussionForumAnswer(DiscussionForumAnswerVO discussionForumAnswerVO);
    int acceptForumSolution(Long forumAnswerId);

    List<Map> getUserAcceptedDiscussionForumAnswers(@Param("userId") Long userId,
                                                    @Param("quarter") Integer quarter,
                                                    @Param("year")Integer year);

    int updateIsAwardedStatus(@Param("forumAnswerId") Long forumAnswerId,@Param("status") boolean status);
}
