package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.other.Suggestion;
import repository.SuggestionRepository;
import service.SuggestionService;

public class SuggestionServiceImpl extends BaseEntityServiceImpl<Integer, Suggestion, SuggestionRepository>
        implements SuggestionService {
    public SuggestionServiceImpl(SuggestionRepository baseRepository) {
        super(baseRepository);
    }
}
