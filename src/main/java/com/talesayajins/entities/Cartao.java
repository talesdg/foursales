package com.talesayajins.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Cartao implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String tipo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="candidato_id")
    private Candidato candidato;

    public Cartao(){

    }
    public Cartao(Integer id, String tipo, Candidato candidato) {
        this.id = id;
        this.tipo = tipo;
        this.candidato = candidato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cartao)) return false;
        Cartao cartao = (Cartao) o;
        return getId().equals(cartao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
