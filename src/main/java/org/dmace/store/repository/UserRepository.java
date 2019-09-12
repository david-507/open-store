package org.dmace.store.repository;

import org.dmace.store.model.User;
import org.springframework.data.repository.CrudRepository;

/** User repository layer with basic CRUD operations */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
