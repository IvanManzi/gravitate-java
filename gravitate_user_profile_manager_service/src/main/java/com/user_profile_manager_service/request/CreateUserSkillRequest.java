package com.user_profile_manager_service.request;

public record CreateUserSkillRequest(String title, String category, String expertise, String certificateUrl) {
}
