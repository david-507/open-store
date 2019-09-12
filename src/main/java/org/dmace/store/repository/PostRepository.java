package org.dmace.store.repository;

import org.dmace.store.model.Post;
import org.springframework.data.repository.CrudRepository;

/** Post repository layer with basic CRUD operations */
public interface PostRepository extends CrudRepository<Post, Long> {
}
