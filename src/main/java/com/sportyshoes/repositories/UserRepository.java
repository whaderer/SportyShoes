package com.sportyshoes.repositories;

import com.sportyshoes.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    // In addition to the CRUD operations provided by extending CrudRepository, User-
    // Repository defines a findByUsername() method that you’ll use in the user details service
    // to look up a User by their username.
    User findByUsername(String username);

//    @Modifying(clearAutomatically = true)
//    @Query("update User user set user.address = ?u., user.age = ?2, user.firstname, user.lastname, user.password, user.username" +
//            " where u.id = ?3")
//    void updateUser(User u);

}
