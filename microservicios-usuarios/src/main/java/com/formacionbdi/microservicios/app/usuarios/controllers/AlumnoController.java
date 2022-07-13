package com.formacionbdi.microservicios.app.usuarios.controllers;

import com.formacionbdi.microservicios.app.usuarios.services.AlumnoService;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @GetMapping("/upload/img/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id) {
        Optional<Alumno> o = this.service.findById(id);
        if (o.isPresent() || o.get().getFoto() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new ByteArrayResource(o.get().getFoto()));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term) {
        return ResponseEntity.ok(this.service.findByNombreOrApellido(term));
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid @RequestBody Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            alumno.setFoto(archivo.getBytes());
        }
        return super.crear(alumno, result);
    }

    @PutMapping("/editar-con-foto/{id}")
    public ResponseEntity<?> crearConFoto(@PathVariable Long id, @Valid @RequestBody Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            alumno.setFoto(archivo.getBytes());
        }
        return super.editar(id, alumno, result);
    }
}