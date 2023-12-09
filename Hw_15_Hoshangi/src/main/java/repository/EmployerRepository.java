package repository;

import base.repository.BaseRepository;
import model.Employer;

public interface EmployerRepository extends BaseRepository<Employer, Integer> {

     Integer salary(Integer id);

     @Override
     Employer login(String userName, String password);


}
