package org.dmace.store.service;

import org.dmace.store.model.Producto;
import org.dmace.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Producto> getRandomProducts(int max) {
        return repository.findRandom(max);
    }

    public List<Producto> findAllByCategory(Long catid) {
        return repository.findAllByCategoria_Id(catid);
    }

    public Optional<Producto> findById(Long id) {
        return repository.findById(id);
    }

    public List<Producto> findAll() {
        return repository.findAll();
    }

    /**
     * Returns products which are from a highlighted Category
     *
     */
    public List<Producto> findFeaturedProducts() {
        return repository.findFeaturedProducts();
    }

    public List<Producto>  findOnSaleProducts() {
        return repository.findOnSaleProducts();
    }

    public Producto save(Producto product) {
        return repository.save(product);
    }

    @Transactional
    public void removeAll(List<Long> ids) {
        repository.removeAllByIdIn(ids);
    }


}
