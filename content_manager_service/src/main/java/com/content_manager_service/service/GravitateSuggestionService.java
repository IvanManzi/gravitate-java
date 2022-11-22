package com.content_manager_service.service;

import com.model.SuggestionVO;
import org.springframework.http.ResponseEntity;

public interface GravitateSuggestionService {

    ResponseEntity createUserSuggestion(SuggestionVO suggestionVO);

    ResponseEntity getAllUserSuggestions();

    ResponseEntity updateUserSuggestion(SuggestionVO suggestionVO);

    ResponseEntity deleteUserSuggestion(Long suggestionId);
}
