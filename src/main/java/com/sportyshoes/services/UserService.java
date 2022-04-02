package com.sportyshoes.services;

import com.sportyshoes.exceptions.ProductNotFoundException;
import com.sportyshoes.models.User;
import com.sportyshoes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new ProductNotFoundException());
    }

//    @Transactional
//    public Optional<User> findById(Long id) {
//        return userRepository.findById(id);
//    }

    //    @Transactional
//    public User getUserByEmailId(String emailId) {
//        return userRepository.getUserByEmailId(emailId);
//    }
//
    @Transactional
    public void updateUser(User userToUpdate, User editedUser) {
        userToUpdate.setAddress(editedUser.getAddress());
        userToUpdate.setAge(editedUser.getAge());
        userToUpdate.setFirstname(editedUser.getFirstname());
        userToUpdate.setLastname(editedUser.getLastname());
        userToUpdate.setPassword(editedUser.getPassword());
        userToUpdate.setUsername(editedUser.getUsername());
        userRepository.save(userToUpdate);
    }

    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

}
