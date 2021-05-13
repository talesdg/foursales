package com.talesayajins.dto;

import com.talesayajins.entities.Cartao;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class CartaoDTO {

    private Integer id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=3, max=10, message="O tamanho deve ser entre 3 e 10 caracteres")
    private String tipo;

    private Integer candidato_id;

    private String candidato;


    public CartaoDTO(){};

    public CartaoDTO(Cartao obj){
        id = obj.getId();
        tipo = obj.getTipo();
        if(obj.getCandidato() != null){
            candidato_id = obj.getCandidato().getId();
            candidato = obj.getCandidato().getNome();
        }
    }

    public CartaoDTO(Integer id, String tipo, Integer candidato_id) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.candidato_id = candidato_id;
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

    public Integer getCandidato_id() {
        return candidato_id;
    }

    public void setCandidato_id(Integer candidato_id) {
        this.candidato_id = candidato_id;
    }

    public String getCandidato() { return candidato; }

    public void setCandidato(String candidato) { this.candidato = candidato; }
}
