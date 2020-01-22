package com.example.whatsappclone.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;

public class ConversasViewHolder extends RecyclerView.ViewHolder {

    public TextView nome, ultimaMensagem, horaUltimaMensagem;
    public ImageView foto;

    public ConversasViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nome = itemView.findViewById(R.id.tv_nome);
        this.ultimaMensagem = itemView.findViewById(R.id.tv_ultima_mensagem);
        this.horaUltimaMensagem = itemView.findViewById(R.id.tv_hora_ultima_mensagem);
        this.foto = itemView.findViewById(R.id.iv_foto);
    }
}