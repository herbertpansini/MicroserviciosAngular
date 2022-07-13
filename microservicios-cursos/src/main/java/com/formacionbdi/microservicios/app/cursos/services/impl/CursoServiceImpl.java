package com.formacionbdi.microservicios.app.cursos.services.impl;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.models.Repository.CursoRepository;
import com.formacionbdi.microservicios.app.cursos.services.CursoService;
import com.formacionbdi.microservicios.commons.services.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Override
    @Transactional(readOnly = true)
    public Curso findByAlumnoId(Long alumnoId) {
        return this.repository.findByAlumnoId(alumnoId);
    }
}