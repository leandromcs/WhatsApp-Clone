package com.example.whatsappclone.model;

import java.util.Map;

public class Conversa {

    private Map<String, String> participantes;

    public Conversa(Map<String, String> participantes) {
        this.participantes = participantes;
    }

    public Map<String, String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Map<String, String> participantes) {
        this.participantes = participantes;
    }
}
