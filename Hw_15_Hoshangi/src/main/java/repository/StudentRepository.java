package repository;

import base.repository.BaseRepository;
import com.sun.xml.bind.v2.model.core.ID;
import model.Lesson;
import model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends BaseRepository<Student,Integer> {

    @Override
    Optional<Student> findById(Integer integer);

    List<Student> loadAll();




}
