package com.example.whatsappclone.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.model.Mensagem;
import com.example.whatsappclone.viewholder.ChatViewHolder;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private List<Mensagem> mensagens;
    private FirebaseAuth auth;

    public ChatAdapter(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
        this.auth = FirebaseAuth.getInstance();
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

        if(auth.getCurrentUser().getPhoneNumber().equals(mensagens.get(position).getOrigem())) {
            holder.ll_chat_content.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.chat_shape_origin));
            holder.ll_chat_item.setGravity(Gravity.END);
        }

        holder.hora.setText(mensagens.get(position).getDataMensagem().getHours() + ":" + mensagens.get(position).getDataMensagem().getMinutes());
    }

    @Override
    public int getItemCount() {
        return mensagens != null ? mensagens.size() : 0;
    }
}
