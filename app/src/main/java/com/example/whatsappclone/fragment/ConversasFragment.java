package com.example.whatsappclone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.adapter.ConversaAdapter;
import com.example.whatsappclone.model.Contato;
import com.example.whatsappclone.model.Conversa;
import com.example.whatsappclone.utils.FirebaseUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConversasFragment extends Fragment {

    private RecyclerView rv;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private List<Conversa> conversas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.conversas_fragment, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.rv = getView().findViewById(R.id.rv_conversas);

        this.rv.setHasFixedSize(true);
        this.rv.setLayoutManager(new LinearLayoutManager(getContext()));

        database.getReference("conversas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.getKey().contains(auth.getCurrentUser().getPhoneNumber())) {
                        Conversa conversa = snapshot.getValue(Conversa.class);

                        List<Contato> contatos = FirebaseUtils.getContactList(getContext().getContentResolver());

                        conversa.setParticipantes(auth.getCurrentUser().getPhoneNumber());
                        conversas.add(conversa);
                    }
                }

                ConversaAdapter adapter = new ConversaAdapter(conversas);
                rv.setAdapter(adapter);
                rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
