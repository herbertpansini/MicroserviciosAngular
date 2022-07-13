package com.formacionbdi.microservicios.app.cursos.controllers;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.services.CursoService;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController extends CommonController<Curso, CursoService> {

    @PutMapping("{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@PathVariable Long id, @RequestBody List<Alumno> alumnos) {
        Optional<Curso> o = this.service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso curso = o.get();
        alumnos.forEach(a -> curso.addAlumno(a));
        return ResponseEntity.ok(this.service.save(curso));
    }

    @PutMapping("{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        Optional<Curso> o = this.service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso curso = o.get();
        curso.removeAlumno(alumno);
        return ResponseEntity.ok(this.service.save(curso));
    }

    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(this.service.findByAlumnoId(alumnoId));
    }

    @PutMapping("{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@PathVariable Long id, @RequestBody List<Examen> examenes) {
        Optional<Curso> o = this.service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso curso = o.get();
        examenes.forEach(e -> curso.addExamen(e));
        return ResponseEntity.ok(this.service.save(curso));
    }

    @PutMapping("{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@PathVariable Long id, @RequestBody Examen examen) {
        Optional<Curso> o = this.service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Curso curso = o.get();
        curso.removeExamen(examen);
        return ResponseEntity.ok(this.service.save(curso));
    }
}