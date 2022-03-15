package com.sportyshoes.repositories;

import com.sportyshoes.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
