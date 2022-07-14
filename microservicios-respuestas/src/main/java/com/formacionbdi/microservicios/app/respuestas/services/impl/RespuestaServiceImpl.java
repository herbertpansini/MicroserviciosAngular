package com.formacionbdi.microservicios.app.respuestas.services.impl;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import com.formacionbdi.microservicios.app.respuestas.models.repository.RespuestaRepository;
import com.formacionbdi.microservicios.app.respuestas.services.RespuestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RespuestaServiceImpl implements RespuestaService {

    private final RespuestaRepository respuestaRepository;

    @Override
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
        return this.respuestaRepository.saveAll(respuestas);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Respuesta> findByAlumnoIdAndExamenId(Long alumnoId, Long examenId) {
        return this.respuestaRepository.findByAlumnoIdAndExamenId(alumnoId, examenId);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Long> findExamenesIdsConRespuestasByAlumnoId(Long alumnoId) {
        return this.respuestaRepository.findExamenesIdsConRespuestasByAlumnoId(alumnoId);
    }
}