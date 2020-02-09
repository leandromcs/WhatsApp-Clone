package com.example.whatsappclone.model;

import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversa conversa = (Conversa) o;
        return mensagens.equals(conversa.mensagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mensagens);
    }
}
