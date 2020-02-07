package com.example.whatsappclone.service;

import com.example.whatsappclone.model.Mensagem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class ChatService {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public void enviarMensagem(String mensagem) {

        String conversaKey = auth.getCurrentUser().getPhoneNumber() + "-" + "+5561123456789";
        DatabaseReference conversaRef = database.getReference("conversas").child(conversaKey);

        DatabaseReference mensagensRef = conversaRef.child("mensagens");
        String mensagemKey = mensagensRef.push().getKey();
        DatabaseReference mensagemRef = mensagensRef.child(mensagemKey);
        Mensagem msg = new Mensagem(auth.getCurrentUser().getPhoneNumber(), "+5561123456789", mensagem, new Date());
        mensagemRef.setValue(msg);
    }
}
