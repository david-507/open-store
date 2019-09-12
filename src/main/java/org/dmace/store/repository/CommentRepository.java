package org.dmace.store.repository;

import org.dmace.store.model.Comment;
import org.springframework.data.repository.CrudRepository;

/** Comment repository layer with basic CRUD operations */
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
