package com.br.manager.model.javabeans;

import java.io.Serializable;

/**
 *
 * @author TPicelli
 */
public class Colaborador extends Usuario implements Serializable {

    public Colaborador(int CdUsuario, String Username, String Password, String Nome, String Email, String Telefone, NivelAcesso nivelacesso) {
        super(CdUsuario, Username, Password, Nome, Email, Telefone, nivelacesso);
    }

    public Colaborador() {
    }

    public Colaborador(int CdUsuario) {
        super(CdUsuario);
    }

}
