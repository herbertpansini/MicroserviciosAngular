package com.formacionbdi.microservicios.app.usuarios.controllers;

import com.formacionbdi.microservicios.app.usuarios.services.AlumnoService;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term) {
        return ResponseEntity.ok(this.service.findByNombreOrApellido(term));
    }
}