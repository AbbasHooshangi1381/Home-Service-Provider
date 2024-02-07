package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.Comments;
import com.example.springbootfinal.domain.userEntity.Admin;

public interface CommentService {
    Comments writCommentForExpert(Integer customerOrderId, Integer expertId, String comments, Integer star);
}
