package com.example.whatsappclone.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.model.Conversa;
import com.example.whatsappclone.model.Mensagem;
import com.example.whatsappclone.viewholder.ConversasViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        List<Mensagem> mensagens = new ArrayList<>(this.conversas.get(position).getMensagens().values());

        holder.nome.setText(mensagens.get(0).getDestino());
        holder.ultimaMensagem.setText(mensagens.get(this.conversas.get(position).getMensagens().size() - 1).getMensagem());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String dataFormatada = sdf.format(mensagens.get(this.conversas.get(position).getMensagens().size() - 1).getDataMensagem());

        holder.horaUltimaMensagem.setText(dataFormatada);

        holder.participantes = this.conversas.get(position).getParticipantes();
    }

    @Override
    public int getItemCount() {
        return conversas != null ? conversas.size() : 0;
    }
}
