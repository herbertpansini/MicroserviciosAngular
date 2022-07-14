package com.formacionbdi.microservicios.app.respuestas.models.repository;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends PagingAndSortingRepository<Respuesta, Long> {

    @Query("SELECT r FROM Respuesta r " +
            "JOIN fetch r.alumno a " +
            "JOIN fetch r.pregunta p " +
            "JOIN fetch p.examen e " +
            "WHERE a.id = :alumnoId " +
            "AND e.id = :examenId ")
    Iterable<Respuesta> findByAlumnoIdAndExamenId(@Param("alumnoId") Long alumnoId, @Param("examenId") Long examenId);

    @Query("SELECT e.id " +
            "FROM Respuesta r " +
            "JOIN r.alumno a " +
            "JOIN r.pregunta p " +
            "JOIN p.examen e " +
            "WHERE a.id = :alumnoId " +
            "GROUP BY e.id ")
    Iterable<Long> findExamenesIdsConRespuestasByAlumnoId(@Param("alumnoId") Long alumnoId);
}
