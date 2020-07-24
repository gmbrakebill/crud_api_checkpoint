package com.galvanize.crud_api_checkpoint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    @Query(value = "SELECT * FROM lessons WHERE id = :id", nativeQuery = true)
    User findById(long id);

    @Query(value = "SELECT id FROM user WHERE (email = :email AND password = :password)", nativeQuery = true)
    public Long authenticate(String email, String password);

}
