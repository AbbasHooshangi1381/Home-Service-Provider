package Q2.service.impl;

import Q2.entity.Teacher;
import Q2.repository.impl.TeacherRepositoryImpl;
import Q2.service.PersonService;

import java.util.List;

public class TeacherServiceImpl implements PersonService<Teacher> {

    TeacherRepositoryImpl teacherRepositoryImpl;

    public TeacherServiceImpl(TeacherRepositoryImpl teacherRepositoryImpl) {
        this.teacherRepositoryImpl = teacherRepositoryImpl;
    }

    @Override
    public void save(Teacher teacher) {
        teacherRepositoryImpl.save(teacher);
    }

    @Override
    public void update(Teacher teacher, Integer id) {
        teacherRepositoryImpl.update(teacher,id);
    }

    @Override
    public Teacher delete(Integer id) {
        return teacherRepositoryImpl.delete(id);
    }

    @Override
    public List<Teacher> loadAll() {
        return teacherRepositoryImpl.loadAll();
    }

    @Override
    public Teacher find(Integer id) {
        return teacherRepositoryImpl.find(id);
    }

    @Override
    public boolean contains(Integer id) {
        return teacherRepositoryImpl.contains(id);
    }
}
