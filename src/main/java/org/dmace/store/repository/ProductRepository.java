package org.dmace.store.repository;

import org.dmace.store.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Producto, Long> {

    @Query(value = "select * from  PRODUCTO order by rand() limit ?1", nativeQuery = true)
    List<Producto> findRandom(int max);

    List<Producto> findAllByCategoria_Id(long catid);
}
