package repository;

import base.repository.BaseRepository;
import model.Lesson;
import model.Teacher;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends BaseRepository<Teacher,Integer> {

    List<Teacher> loadAll();

    @Override
    void deleteById(Integer integer);

    @Override
    Teacher saveOrUpdate(Teacher entity);

    @Override
    Optional<Teacher> findById(Integer integer);

    //SelectedLessonRepository addGrade(Integer ID , Integer score);

    @Override
    Teacher login(String userName, String password);

    @Override
    Collection<Teacher> findAll();

//double calculateSalary(Integer term);



}
