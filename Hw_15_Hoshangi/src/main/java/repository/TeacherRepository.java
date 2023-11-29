package repository;

import base.repository.BaseRepository;
import model.Lesson;
import model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends BaseRepository<Teacher,Integer> {

    List<Teacher> loadAll();

    @Override
    Optional<Teacher> findById(Integer integer);

    //SelectedLessonRepository addGrade(Integer ID , Integer score);


    //double calculateSalary(Integer term);



}
