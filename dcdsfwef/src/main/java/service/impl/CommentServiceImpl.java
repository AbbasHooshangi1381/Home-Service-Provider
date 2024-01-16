package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.other.Comments;
import repository.CommentsRepository;
import service.CommentService;

public class CommentServiceImpl extends BaseEntityServiceImpl<Integer, Comments, CommentsRepository>
        implements CommentService {
    public CommentServiceImpl(CommentsRepository baseRepository) {
        super(baseRepository);
    }
}
