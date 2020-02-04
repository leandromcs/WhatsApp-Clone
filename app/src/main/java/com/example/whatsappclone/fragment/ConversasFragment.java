package com.example.whatsappclone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.activity.ChatActivity;
import com.example.whatsappclone.adapter.ConversaAdapter;
import com.example.whatsappclone.model.Conversa;
import com.example.whatsappclone.model.Mensagem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConversasFragment extends Fragment {

    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.conversas_fragment, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.rv = getView().findViewById(R.id.rv_conversas);
        this.rv.setHasFixedSize(true);
        this.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Mensagem> mensagens = new ArrayList<>();
        Mensagem mensagem1 = new Mensagem("Leandro", "Joãozinho", "Testando 123", new Date());
        Mensagem mensagem2 = new Mensagem("Leandro", "Joãozinho", "321 Testando", new Date());
        mensagens.add(mensagem1);
        mensagens.add(mensagem2);
        ConversaAdapter adapter = new ConversaAdapter(mensagens);
        this.rv.setAdapter(adapter);

        this.rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
}
