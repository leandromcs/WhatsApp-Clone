package com.example.whatsappclone.model;

import java.util.Map;

public class Conversa {

    private String participantes;
    private Map<String, Mensagem> mensagens;

    public Conversa() {
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    public Map<String, Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(Map<String, Mensagem> mensagens) {
        this.mensagens = mensagens;
    }
}
