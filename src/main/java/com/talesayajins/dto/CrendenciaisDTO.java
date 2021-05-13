package com.talesayajins.dto;

import java.io.Serializable;

public class CrendenciaisDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String senha;

    public CrendenciaisDTO(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
