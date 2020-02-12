package com.example.whatsappclone.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout ll_chat_item, ll_chat_content;
    public TextView mensagem, hora;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);

        this.ll_chat_item = itemView.findViewById(R.id.ll_chat_item);
        this.ll_chat_content = itemView.findViewById(R.id.ll_chat_content);
        this.mensagem = itemView.findViewById(R.id.tv_mensagem);
        this.hora = itemView.findViewById(R.id.tv_hora);
    }
}
