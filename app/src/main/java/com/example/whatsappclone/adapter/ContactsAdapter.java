package com.example.whatsappclone.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.model.Contato;
import com.example.whatsappclone.model.Mensagem;
import com.example.whatsappclone.viewholder.ContactsViewHolder;
import com.example.whatsappclone.viewholder.ConversasViewHolder;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {

    private final List<Contato> contatos;

    public ContactsAdapter(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.nome.setText(contatos.get(position).getNome());
        holder.tel.setText(contatos.get(position).getTel());
    }

    @Override
    public int getItemCount() {
        return contatos != null ? contatos.size() : 0;
    }
}
