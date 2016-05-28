package com.br.manager.model.javabeans;

import java.io.Serializable;

/**
 *
 * @author TPicelli
 */
public abstract class Usuario implements Serializable {

    private int CdUsuario;
    private String Username;
    private String Password;
    private String Nome;
    private String Email;
    private String Telefone;
    private NivelAcesso nivelAcesso;
    public enum NivelAcesso {
        ADMINISTRADOR, GERENTE, DESENVOLVEDOR;
    }

    public Usuario() {
    }

    public Usuario(int CdUsuario) {
        this.CdUsuario = CdUsuario;
    }

    public Usuario(int CdUsuario, String Username, String Password, String Nome, String Email, String Telefone, NivelAcesso nivelAcesso) {
        this.CdUsuario = CdUsuario;
        this.Username = Username;
        this.Password = Password;
        this.Nome = Nome;
        this.Email = Email;
        this.Telefone = Telefone;
        this.nivelAcesso = nivelAcesso;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
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

    @Override
    public String toString() {
        return "Usuario{" + "CdUsuario=" + CdUsuario + ", Username=" + Username + ", Password=" + Password + ", Email=" + Email + ", Telefone=" + Telefone + '}';
    }
    
}
