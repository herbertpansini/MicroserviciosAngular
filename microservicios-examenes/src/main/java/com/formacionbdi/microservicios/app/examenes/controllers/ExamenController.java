package com.formacionbdi.microservicios.app.examenes.controllers;

import com.formacionbdi.microservicios.app.examenes.services.ExamenService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/examenes")
public class ExamenController extends CommonController<Examen, ExamenService> {

    @PutMapping("{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Examen examen) {
        Optional<Examen> o = this.service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Examen examenDb = o.get();
        examenDb.setNombre(examen.getNombre());

        examenDb.getPreguntas()
                .stream()
                .filter(p -> !examen.getPreguntas().contains(p))
                .forEach(examenDb::removePregunta);

        examenDb.setPreguntas(examen.getPreguntas());
        return ResponseEntity.ok(this.service.save(examenDb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term) {
        return ResponseEntity.ok(this.service.findByNombreContainingIgnoreCaseOrderByNombreAsc(term));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas() {
        return ResponseEntity.ok(this.service.findAllAsignaturas());
    }
}