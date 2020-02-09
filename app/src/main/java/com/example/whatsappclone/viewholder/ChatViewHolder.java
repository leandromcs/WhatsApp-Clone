package com.example.whatsappclone.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    public TextView mensagem, hora;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mensagem = itemView.findViewById(R.id.tv_mensagem);
        this.hora = itemView.findViewById(R.id.tv_hora);
    }
}
