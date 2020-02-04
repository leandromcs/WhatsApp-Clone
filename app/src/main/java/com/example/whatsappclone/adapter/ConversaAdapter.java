package com.example.whatsappclone.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.model.Conversa;
import com.example.whatsappclone.model.Mensagem;
import com.example.whatsappclone.viewholder.ConversasViewHolder;

import java.util.List;

public class ConversaAdapter extends RecyclerView.Adapter<ConversasViewHolder> {

    private final List<Mensagem> mensagens;

    public ConversaAdapter(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    @Override
    public ConversasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ConversasViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conversa_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ConversasViewHolder holder, int position) {
        holder.nome.setText("Nome Provisório");
        holder.ultimaMensagem.setText(this.mensagens.get(this.mensagens.size()-1).getMensagem());
        holder.horaUltimaMensagem.setText(this.mensagens.get(this.mensagens.size()-1).getDataMensagem().toString());
    }

    @Override
    public int getItemCount() {
        return mensagens != null ? mensagens.size() : 0;
    }
}
