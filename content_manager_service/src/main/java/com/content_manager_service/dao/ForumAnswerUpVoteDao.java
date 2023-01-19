package com.content_manager_service.dao;

import com.model.ForumAnswerUpVoteVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ForumAnswerUpVoteDao {

    int createForumAnswerUpVote(ForumAnswerUpVoteVO forumAnswerUpVoteVO);

    int checkIfUserMadeForumAnswerUpVote(ForumAnswerUpVoteVO forumAnswerUpVoteVO);
}
