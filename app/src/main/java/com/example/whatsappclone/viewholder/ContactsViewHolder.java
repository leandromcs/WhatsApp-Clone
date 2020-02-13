package com.example.whatsappclone.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.activity.ChatActivity;

public class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nome, tel;
    public ImageView foto;

    public ContactsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nome = itemView.findViewById(R.id.tv_nome);
        this.tel = itemView.findViewById(R.id.tv_tel);
        this.foto = itemView.findViewById(R.id.iv_foto);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ChatActivity.class);
        intent.putExtra("nome", nome.getText());
        intent.putExtra("tel", tel.getText());
        v.getContext().startActivity(intent);
    }
}
