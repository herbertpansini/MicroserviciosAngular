package com.formacionbdi.microservicios.commons.examenes.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "preguntas")
@Getter
@Setter
@EqualsAndHashCode
public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    @JsonIgnoreProperties(value = {"preguntas"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examen_id")
    private Examen examen;
}
