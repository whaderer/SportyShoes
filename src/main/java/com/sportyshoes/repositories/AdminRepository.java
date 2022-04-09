package com.sportyshoes.repositories;

import com.sportyshoes.models.Admin;
import com.sportyshoes.models.User;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    // In addition to the CRUD operations provided by extending CrudRepository, User-
    // Repository defines a findByUsername() method that you’ll use in the user details service
    // to look up a User by their username.
   // Admin findByUsername(String username);
}
