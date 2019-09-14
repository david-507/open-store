package org.dmace.store.service;

import org.dmace.store.model.User;
import org.dmace.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findByEmail(String username) {
        return repository.findByEmailOrName(username, username);
    }
}
