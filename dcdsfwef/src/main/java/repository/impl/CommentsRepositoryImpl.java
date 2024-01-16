package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.other.Comments;
import repository.CommentsRepository;

import javax.persistence.EntityManager;

public class CommentsRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Comments> implements CommentsRepository {
    public CommentsRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Comments> getEntityClass() {
        return Comments.class;
    }
}
