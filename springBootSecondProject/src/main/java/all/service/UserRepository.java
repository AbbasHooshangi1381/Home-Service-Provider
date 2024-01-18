package all.service;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public void getUsers(){
        System.out.println("getting users from DB");
    }

}
