package com.tbs.user.repository;

import com.tbs.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    boolean existsByEmail(String email);
}
