package com.example.whatsappclone.model;

import java.util.Date;

public class Mensagem {

    private String origem;
    private String destino;
    private String mensagem;
    private Date dataMensagem;

    public Mensagem() {
    }

    public Mensagem(String origem, String destino, String mensagem, Date dataMensagem) {
        this.origem = origem;
        this.destino = destino;
        this.mensagem = mensagem;
        this.dataMensagem = dataMensagem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataMensagem() {
        return dataMensagem;
    }

    public void setDataMensagem(Date dataMensagem) {
        this.dataMensagem = dataMensagem;
    }
}
