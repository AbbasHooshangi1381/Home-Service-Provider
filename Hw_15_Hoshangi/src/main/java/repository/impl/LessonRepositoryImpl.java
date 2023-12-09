package repository.impl;

import base.repository.BaseRepositoryImpl;
import lombok.NoArgsConstructor;
import model.Lesson;
import repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
@NoArgsConstructor

public class LessonRepositoryImpl extends BaseRepositoryImpl<Lesson, Integer> implements LessonRepository {


    public LessonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Lesson> getEntityClass() {
        return Lesson.class;
    }


    @Override
    public Lesson saveOrUpdate(Lesson entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        super.deleteById(integer);
    }

    @Override
    public List<Lesson> loadAllLessons() {
        TypedQuery<Lesson> query =
                entityManager.createQuery("select L from  Lesson  L ", Lesson.class);
        return query.getResultList();
    }

}
