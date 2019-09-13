package org.dmace.store.service;

import org.dmace.store.model.Categoria;
import org.dmace.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public void create(Categoria category) {
        repository.save(category);
    }

    public int countProductsByCategory(Categoria category) {
        return repository.numProductsByCategory(category);
    }

    public int countProductsByCategory(Long catid) {
        return repository.countProductsByCategory(catid);
    }

    public Optional<Categoria> findById(Long cid) {
        return repository.findById(cid);
    }

    public void delete(Categoria categoria) {
        repository.delete(categoria);
    }
}
