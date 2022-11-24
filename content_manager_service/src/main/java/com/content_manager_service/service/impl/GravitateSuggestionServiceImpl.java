package com.content_manager_service.service.impl;

import com.content_manager_service.service.GravitateSuggestionService;
import com.model.SuggestionVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GravitateSuggestionServiceImpl implements GravitateSuggestionService {



    @Override
    public ResponseEntity createUserSuggestion(SuggestionVO suggestionVO) {
        return null;
    }

    @Override
    public ResponseEntity getAllUserSuggestions() {
        return null;
    }

    @Override
    public ResponseEntity updateUserSuggestion(SuggestionVO suggestionVO) {
        return null;
    }

    @Override
    public ResponseEntity deleteUserSuggestion(Long suggestionId) {
        return null;
    }
}
