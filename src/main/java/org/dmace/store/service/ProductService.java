package org.dmace.store.service;

import org.dmace.store.model.Producto;
import org.dmace.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Producto> getRandomProducts(int max) {

        return repository.findRandom(max);

    }
}
