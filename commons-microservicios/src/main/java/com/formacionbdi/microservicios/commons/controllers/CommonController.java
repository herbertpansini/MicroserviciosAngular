package com.formacionbdi.microservicios.commons.controllers;

import com.formacionbdi.microservicios.commons.services.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonController<E, S extends CommonService<E>> {

    @Autowired
    protected S service;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/pagina")
    public ResponseEntity<?> listar(Pageable pageable) {
        return ResponseEntity.ok(this.service.findAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<E> o = this.service.findById(id);
        return o.isPresent() ? ResponseEntity.ok(o.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @Valid @RequestBody E entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Optional<E> o = this.service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        E entityDb = o.get();
        BeanUtils.copyProperties(entity, entityDb, "id");
        return ResponseEntity.ok(this.service.save(entityDb));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e -> errores.put(e.getField(), "El campo " +  e.getField() + " " + e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}