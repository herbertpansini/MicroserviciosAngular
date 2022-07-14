package com.formacionbdi.microservicios.app.respuestas.services;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaService {

    Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);

    Iterable<Respuesta> findByAlumnoIdAndExamenId(Long alumnoId, Long examenId);

    Iterable<Long> findExamenesIdsConRespuestasByAlumnoId(Long alumnoId);
}
