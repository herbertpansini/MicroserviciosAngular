package com.formacionbdi.microservicios.commons.alumnos.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "alumnos")
@Getter
@Setter
@EqualsAndHashCode
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @Email
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Lob
    @JsonIgnore
    private byte[] foto;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    public Integer getFotoHashCode() {
        return this.foto != null ? this.foto.hashCode() : null;
    }
}