package com.formacionbdi.microservicios.app.usuarios.models.repository;

import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
    @Query("SELECT a FROM Alumno a WHERE a.nombre LIKE UPPER(CONCAT(CONCAT('%', :term), '%')) OR a.apellido LIKE UPPER(CONCAT(CONCAT('%', :term), '%'))")
    List<Alumno> findByNombreOrApellido(@Param("term") String term);
}