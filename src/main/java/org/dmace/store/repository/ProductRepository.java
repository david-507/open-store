package org.dmace.store.repository;

import org.dmace.store.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Producto, Long> {

    @Query(value = "select * from  PRODUCTO order by random() limit ?1", nativeQuery = true)
    List<Producto> findRandom(int max);

    @Query("select p from Producto p order by random")
    List<Producto> findRandomProducts();

    List<Producto> findAllByCategoria_Id(long catid);

    List<Producto> findAllByIdIsIn(List<Long> ids);

    void removeAllByIdIn(List<Long> ids);
}
