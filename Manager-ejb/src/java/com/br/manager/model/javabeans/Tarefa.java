package com.br.manager.model.javabeans;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author TPicelli
 */
public class Tarefa implements Serializable{
    private int CdTarefa;
    private String Nome;
    private String Descricao;
    private Date DtFinal;
    private Colaborador Colaborador;
    private Status Status;
    public enum Status {
        NAO_INICIADA, INICIADA, INCOMPLETA, FINALIZANDO, TESTE, FINALIZADA, CANCELADA;
    }
    
    public Tarefa() {
    }
    
    public Tarefa(int CdTarefa, String Nome, String Descricao, Date DtFinal, Colaborador Colaborador, Status Status) {
        this.CdTarefa = CdTarefa;
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.DtFinal = DtFinal;
        this.Colaborador = Colaborador;
        this.Status = Status;
    }

    public int getCdTarefa() {
        return CdTarefa;
    }

    public void setCdTarefa(int CdTarefa) {
        this.CdTarefa = CdTarefa;
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

    public Date getDtFinal() {
        return DtFinal;
    }

    public void setDtFinal(Date DtFinal) {
        this.DtFinal = DtFinal;
    }

    public Colaborador getColaborador() {
        return Colaborador;
    }

    public void setColaborador(Colaborador Colaborador) {
        this.Colaborador = Colaborador;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Tarefa{" + "CdTarefa=" + CdTarefa + ", Nome=" + Nome + ", Descricao=" + Descricao + ", DtFinal=" + DtFinal + ", Colaborador=" + Colaborador + ", Status=" + Status + '}';
    }

}
