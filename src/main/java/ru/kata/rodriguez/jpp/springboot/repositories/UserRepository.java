package ru.kata.rodriguez.jpp.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.rodriguez.jpp.springboot.models.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("Select u from User u left join fetch u.roles where u.firstName=:name")
    Optional<User> findByName(String name);
}