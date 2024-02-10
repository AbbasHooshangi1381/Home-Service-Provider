package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.Comments;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.Admin.BaseSaveDto;
import com.example.springbootfinal.dto.comments.CommentsRequestDto;
import com.example.springbootfinal.dto.comments.CommentsResponseDto;
import com.example.springbootfinal.repository.CommentsRepository;
import com.example.springbootfinal.service.CommentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentService commentService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CommentsRepository commentsRepository;

    @PostMapping("/register-comments")
    public ResponseEntity<Integer> saveComments(@Valid @RequestBody CommentsRequestDto commentsRequestDto) {
        commentService.writCommentForExpert(commentsRequestDto.getCustomerOrderId(), commentsRequestDto.getExpertId(),
                commentsRequestDto.getComments(), commentsRequestDto.getStar());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentsRequestDto.getStar());
    }
}
