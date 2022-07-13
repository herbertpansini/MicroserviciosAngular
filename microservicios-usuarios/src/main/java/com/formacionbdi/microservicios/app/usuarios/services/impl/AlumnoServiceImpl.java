package com.formacionbdi.microservicios.app.usuarios.services.impl;

import com.formacionbdi.microservicios.app.usuarios.models.repository.AlumnoRepository;
import com.formacionbdi.microservicios.app.usuarios.services.AlumnoService;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.services.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return this.repository.findByNombreOrApellido(term);
    }
}