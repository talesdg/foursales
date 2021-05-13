package com.talesayajins.dto;

import com.talesayajins.entities.Candidato;
import com.talesayajins.entities.Cartao;
import com.talesayajins.services.validation.CandidatoInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@CandidatoInsert
public class CandidatoNewDTO {

    private Integer id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=50, message="O tamanho deve ser entre 5 e 50 caracteres")
    private String nome;


    private String cpf;

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;

    private List<Cartao> cartoes;

    public CandidatoNewDTO(){}

    public CandidatoNewDTO(Candidato obj){
        id = obj.getId();
        nome = obj.getNome();
        cpf = obj.getCpf();
        email = obj.getEmail();
        cartoes = obj.getCartoes();
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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }
}
