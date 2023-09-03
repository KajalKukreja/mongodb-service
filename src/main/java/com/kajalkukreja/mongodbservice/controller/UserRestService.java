package com.kajalkukreja.mongodbservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.kajalkukreja.mongodbservice.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kajalkukreja.mongodbservice.model.User;

@RestController
@RequestMapping("/users")
public class UserRestService {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @GetMapping("/{userId}")
    public User getUser(@NotNull @PathVariable("userId") final Long userId) {
        System.out.println("Looking for user with id " + userId);
        final Optional<User> users = userDao.findById(userId);
        return users.orElse(null);
    }

    @PostMapping("/add")
    public User saveUser(@NotNull @RequestBody final User user) {
        final long count = userDao.count();
        user.setId(count+1);
        final User savedUser = userDao.save(user);
        System.out.println("Saved user");
        return savedUser;
    }

    @DeleteMapping("/remove/{userId}")
    public User deleteUser(@NotNull @PathVariable("userId") final Long userId) {
        final User user = userDao.findById(userId).orElse(null);
        if (user != null) {
            userDao.deleteById(userId);
            System.out.println("User deleted");
        }
        return user;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}