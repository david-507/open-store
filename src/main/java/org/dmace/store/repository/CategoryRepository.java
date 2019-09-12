package org.dmace.store.repository;

import org.dmace.store.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Categoria, Long> {
}
