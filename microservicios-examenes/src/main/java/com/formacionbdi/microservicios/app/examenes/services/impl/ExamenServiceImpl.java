package com.formacionbdi.microservicios.app.examenes.services.impl;

import com.formacionbdi.microservicios.app.examenes.models.repository.AsignaturaRepository;
import com.formacionbdi.microservicios.app.examenes.models.repository.ExamenRepository;
import com.formacionbdi.microservicios.app.examenes.services.ExamenService;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Asignatura;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import com.formacionbdi.microservicios.commons.services.impl.CommonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

    private final AsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombreContainingIgnoreCaseOrderByNombreAsc(String nombre) {
        return this.repository.findByNombreContainingIgnoreCaseOrderByNombreAsc(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asignatura> findAllAsignaturas() {
        return this.asignaturaRepository.findAll();
    }
}
