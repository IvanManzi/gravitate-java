package com.content_manager_service.dao;

import com.model.WishReplyVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishReplyDao {

    int saveWishReply(WishReplyVO wishReplyVO);
}
