package com.br.manager.model.javabeans;

import java.io.Serializable;

/**
 *
 * @author TPicelli
 */
public abstract class Usuario implements Serializable{
    private int CdUsuario;
    private String Username;
    private String Password;
    private String Email;
    private String Telefone;

    public Usuario() {
    }

    public Usuario(int CdUsuario, String Username, String Password, String Email, String Telefone) {
        this.CdUsuario = CdUsuario;
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.Telefone = Telefone;
    }

    public int getCdUsuario() {
        return CdUsuario;
    }

    public void setCdUsuario(int CdUsuario) {
        this.CdUsuario = CdUsuario;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }
}
