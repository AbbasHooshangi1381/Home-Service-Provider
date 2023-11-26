package repository;

import base.repository.BaseRepository;
import model.Student;
import model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends BaseRepository<Teacher,Integer> {

    List<Teacher> loadAll();

    @Override
    Optional<Teacher> findById(Integer integer);

    void addGrade(Integer ID , Integer score);


    Double calculateSalary(Integer term);



}
