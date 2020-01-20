package com.example.whatsappclone.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.model.Conversa;
import com.example.whatsappclone.viewholder.ConversasViewHolder;

import java.util.List;

public class ConversaAdapter extends RecyclerView.Adapter<ConversasViewHolder> {

    private final List<Conversa> conversas;

    public ConversaAdapter(List<Conversa> conversas) {
        this.conversas = conversas;
    }

    @Override
    public ConversasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ConversasViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conversa_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ConversasViewHolder holder, int position) {
        holder.nome.setText(this.conversas.get(position).getNome());
        holder.ultimaMensagem.setText(this.conversas.get(position).getUltimaMensagem());
        holder.horaUltimaMensagem.setText(this.conversas.get(position).getHoraUltimaMensagem());
    }

    @Override
    public int getItemCount() {
        return conversas != null ? conversas.size() : 0;
    }
}
