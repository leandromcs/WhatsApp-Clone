package com.example.whatsappclone.model;

public class Conversa {

    private String nome, ultimaMensagem, horaUltimaMensagem;

    public Conversa(String nome, String ultimaMensagem, String horaUltimaMensagem) {
        this.nome = nome;
        this.ultimaMensagem = ultimaMensagem;
        this.horaUltimaMensagem = horaUltimaMensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }

    public void setUltimaMensagem(String ultimaMensagem) {
        this.ultimaMensagem = ultimaMensagem;
    }

    public String getHoraUltimaMensagem() {
        return horaUltimaMensagem;
    }

    public void setHoraUltimaMensagem(String horaUltimaMensagem) {
        this.horaUltimaMensagem = horaUltimaMensagem;
    }
}
