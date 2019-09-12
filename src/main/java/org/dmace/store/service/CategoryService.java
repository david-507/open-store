package org.dmace.store.service;

import org.dmace.store.model.Categoria;
import org.dmace.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Categoria> findAll() {
        return repository.findAll();
    }

}
