package com.formacionbdi.microservicios.app.examenes.models.repository;

import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long> {

    List<Examen> findByNombreContainingIgnoreCaseOrderByNombreAsc(String nombre);
}
