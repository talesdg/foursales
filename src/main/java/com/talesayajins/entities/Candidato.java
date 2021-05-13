package com.talesayajins.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column(unique=true)
    private String cpf;

    @Column(unique=true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy="candidato", fetch = FetchType.EAGER)
    private List<Cartao> cartoes = new ArrayList<>();

    public Candidato() {

    }

    public Candidato(Integer id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidato)) return false;
        Candidato candidato = (Candidato) o;
        return getId().equals(candidato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
