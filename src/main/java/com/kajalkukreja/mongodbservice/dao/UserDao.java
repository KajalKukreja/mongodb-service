package com.kajalkukreja.mongodbservice.dao;

import com.kajalkukreja.mongodbservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, Long> {

}
