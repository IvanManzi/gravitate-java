package com.content_manager_service.dao;

import com.model.SuggestionVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuggestionDao {

    int createSuggestion(SuggestionVO suggestionVO);
}
