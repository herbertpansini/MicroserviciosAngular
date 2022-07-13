package com.formacionbdi.microservicios.app.cursos.models.Repository;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends PagingAndSortingRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c JOIN fetch c.alumnos a WHERE a.id = :alumnoId")
    Curso findByAlumnoId(@Param("alumnoId") Long alumnoId);
}