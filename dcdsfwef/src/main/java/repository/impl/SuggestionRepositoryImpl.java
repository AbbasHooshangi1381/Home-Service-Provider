package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.other.Comments;
import domain.other.Suggestion;
import repository.CommentsRepository;
import repository.SuggestionRepository;

import javax.persistence.EntityManager;

public class SuggestionRepositoryImpl  extends BaseEntityRepositoryImpl<Integer, Suggestion> implements SuggestionRepository {
    public SuggestionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Suggestion> getEntityClass() {
        return Suggestion.class;
    }
}
