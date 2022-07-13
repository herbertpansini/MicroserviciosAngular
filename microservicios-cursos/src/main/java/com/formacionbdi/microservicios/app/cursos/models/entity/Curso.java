package com.formacionbdi.microservicios.app.cursos.models.entity;

import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@EqualsAndHashCode
public class Curso implements Serializable {
    private static final long serialVersioUID = 1L;

    public Curso() {
        this.alumnos = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    public void addAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public void removeAlumno(Alumno alumno) {
        this.alumnos.remove(alumno);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }
}
