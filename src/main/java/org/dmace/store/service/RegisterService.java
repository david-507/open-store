package org.dmace.store.service;

import org.dmace.store.model.User;
import org.dmace.store.model.bean.RegisterBean;
import org.dmace.store.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    private UserRepository repository;

    /** tries to create new user */
    public User register(RegisterBean rb) throws DataIntegrityViolationException {
        User user = toUser(rb);
        return repository.save(user);
    }

    /** Creates a new User instance with the given Bean values */
    private User toUser(RegisterBean rb) {
        return new User(rb.getName(), rb.getEmail(), rb.getCity(), BCrypt.hashpw(rb.getPassword(), BCrypt.gensalt()));
    }

}
