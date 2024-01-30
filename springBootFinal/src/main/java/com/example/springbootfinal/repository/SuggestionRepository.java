package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface SuggestionRepository  extends JpaRepository< Suggestion, Integer> {

}
