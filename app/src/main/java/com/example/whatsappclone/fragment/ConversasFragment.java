package com.example.whatsappclone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.adapter.ConversaAdapter;
import com.example.whatsappclone.model.Conversa;

import java.util.ArrayList;
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
        List<Conversa> conversas = new ArrayList<>();
        Conversa conversa1 = new Conversa("Leandro", "Bom dia", "23:59");
        Conversa conversa2 = new Conversa("Joãozinho", "Bom dia", "00:00");
        conversas.add(conversa1);
        conversas.add(conversa2);
        ConversaAdapter adapter = new ConversaAdapter(conversas);
        this.rv.setAdapter(adapter);
    }
}
