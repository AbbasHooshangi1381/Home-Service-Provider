package com.example.springbootfinal.service;

public interface CommentService {
    void writCommentForExpert(Integer customerOrderId, Integer expertId, String comments, Integer star);
}
