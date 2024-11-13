package com.es2.pautas_e_atas.domain.Usuario;

public enum TipoUsuario {
    GERENTE("gerente"),
    MEMBRO("membro");

    private String usuario;

    TipoUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }
}
