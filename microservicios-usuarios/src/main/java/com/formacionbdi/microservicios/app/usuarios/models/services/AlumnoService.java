package com.formacionbdi.microservicios.app.usuarios.models.services;

import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;

import java.util.Optional;

public interface AlumnoService {
    Iterable<Alumno> findAll();

    Optional<Alumno> findById(Long id);

    Alumno save(Alumno alumno);

    void deleteById(Long id);
}