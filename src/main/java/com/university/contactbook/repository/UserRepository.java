package com.university.contactbook.repository;

import com.university.contactbook.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsernameAndDeletedFalse(String username);

    List<User> findAllByDeletedFalse();
}
