package com.br.manager.model.javabeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author TPicelli
 */
public class Projeto implements Serializable {

    private int CdProjeto;
    private String Nome;
    private String Descricao;
    private Date DtInicio;
    private Date DtPrevisaoFim;
    private Colaborador Gerente;
    private ArrayList<Colaborador> Usuarios;
    private ArrayList<Tarefa> Tarefas;

    public Projeto() {
    }

    public Projeto(int CdProjeto, String Nome, String Descricao, Date DtInicio, Date DtPrevisaoFim, Colaborador Gerente, ArrayList<Colaborador> Usuarios, ArrayList<Tarefa> Tarefas) {
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

    public Colaborador getGerente() {
        return Gerente;
    }

    public void setGerente(Colaborador Gerente) {
        this.Gerente = Gerente;
    }

    public ArrayList<Colaborador> getUsuarios() {
        return Usuarios;
    }

    public void setUsuarios(ArrayList<Colaborador> Usuarios) {
        this.Usuarios = Usuarios;
    }

    public ArrayList<Tarefa> getTarefas() {
        return Tarefas;
    }

    public void setTarefas(ArrayList<Tarefa> Tarefas) {
        this.Tarefas = Tarefas;
    }

    @Override
    public String toString() {
        return "Projeto{" + "CdProjeto=" + CdProjeto + ", Nome=" + Nome + ", Descricao=" + Descricao + ", DtInicio=" + DtInicio + ", DtPrevisaoFim=" + DtPrevisaoFim + ", Gerente=" + Gerente + ", Usuarios=" + Usuarios + ", Tarefas=" + Tarefas + '}';
    }

}
