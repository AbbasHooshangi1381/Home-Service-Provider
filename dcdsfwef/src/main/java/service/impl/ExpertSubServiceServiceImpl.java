package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.other.Comments;
import domain.other.ExpertSubService;
import repository.CommentsRepository;
import repository.ExpertRepository;
import repository.ExpertSubServiceRepository;
import service.CommentService;
import service.ExpertSubServiceService;

public class ExpertSubServiceServiceImpl extends BaseEntityServiceImpl<Integer, ExpertSubService, ExpertSubServiceRepository>
        implements ExpertSubServiceService {
    public ExpertSubServiceServiceImpl(ExpertSubServiceRepository baseRepository) {
        super(baseRepository);
    }
}
