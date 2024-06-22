package com.example.loanapproval.repository;

import com.example.loanapproval.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

}
