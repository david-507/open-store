package org.dmace.store.repository;

import org.dmace.store.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "select count(1) from PRODUCTO p where p.CATEGORIA_ID = ?1", nativeQuery = true)
    int countProductsByCategory(Long catid);

    @Query("select count(p) from Producto p where p.categoria = ?1")
    int numProductsByCategory(Categoria categoria);
}
