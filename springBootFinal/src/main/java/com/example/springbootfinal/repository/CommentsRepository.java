package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsRepository  extends JpaRepository<Comments,Integer> {
    Optional<Comments>findByComments(String comments);
}
