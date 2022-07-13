package com.formacionbdi.microservicios.commons.services.impl;

import com.formacionbdi.microservicios.commons.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements CommonService<E> {

    @Autowired
    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public E save(E entity) {
        return this.repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}