package com.example.whatsappclone.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.model.Mensagem;
import com.example.whatsappclone.viewholder.ChatViewHolder;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private List<Mensagem> mensagens;

    public ChatAdapter(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.mensagem.setText(mensagens.get(position).getMensagem());
        holder.hora.setText(Integer.toString(mensagens.get(position).getDataMensagem().getHours()) + ":" + Integer.toString(mensagens.get(position).getDataMensagem().getMinutes()));
    }

    @Override
    public int getItemCount() {
        return mensagens != null ? mensagens.size() : 0;
    }
}
