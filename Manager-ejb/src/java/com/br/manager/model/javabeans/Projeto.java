package com.br.manager.model.javabeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author TPicelli
 */
public class Projeto implements Serializable{
    private int CdProjeto;
    private String Nome;
    private String Descricao;
    private Date DtInicio;
    private Date DtPrevisaoFim;
    private Gerente Gerente;
    private ArrayList<Usuario> Usuarios;
    private ArrayList<Tarefa> Tarefas;

    public Projeto() {
    }

    public Projeto(int CdProjeto, String Nome, String Descricao, Date DtInicio, Date DtPrevisaoFim, Gerente Gerente, ArrayList<Usuario> Usuarios, ArrayList<Tarefa> Tarefas) {
        this.CdProjeto = CdProjeto;
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.DtInicio = DtInicio;
        this.DtPrevisaoFim = DtPrevisaoFim;
        this.Gerente = Gerente;
        this.Usuarios = Usuarios;
        this.Tarefas = Tarefas;
    }

    public int getCdProjeto() {
        return CdProjeto;
    }

    public void setCdProjeto(int CdProjeto) {
        this.CdProjeto = CdProjeto;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Date getDtInicio() {
        return DtInicio;
    }

    public void setDtInicio(Date DtInicio) {
        this.DtInicio = DtInicio;
    }

    public Date getDtPrevisaoFim() {
        return DtPrevisaoFim;
    }

    public void setDtPrevisaoFim(Date DtPrevisaoFim) {
        this.DtPrevisaoFim = DtPrevisaoFim;
    }

    public Gerente getGerente() {
        return Gerente;
    }

    public void setGerente(Gerente Gerente) {
        this.Gerente = Gerente;
    }

    public ArrayList<Usuario> getUsuarios() {
        return Usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> Usuarios) {
        this.Usuarios = Usuarios;
    }

    public ArrayList<Tarefa> getTarefas() {
        return Tarefas;
    }

    public void setTarefas(ArrayList<Tarefa> Tarefas) {
        this.Tarefas = Tarefas;
    }

    
}
