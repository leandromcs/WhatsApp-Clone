package com.example.whatsappclone.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.adapter.ChatAdapter;
import com.example.whatsappclone.model.Conversa;
import com.example.whatsappclone.model.Mensagem;
import com.example.whatsappclone.service.ChatService;
import com.example.whatsappclone.utils.FirebaseUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private TextView nome;
    private EditText mensagem;
    private FloatingActionButton btnEnviar;
    private ChatService service = new ChatService();

    private RecyclerView rv;
    private ChatAdapter adapter;

    private Conversa conversa;
    private List<Mensagem> mensagens = new ArrayList<>();
    private String destino;

    private Boolean firstMessage = true;

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.chat_toolbar));

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.btnEnviar = findViewById(R.id.btn_enviar);
        this.mensagem = findViewById(R.id.et_mensagem);
        this.nome = findViewById(R.id.tv_nome);

        Bundle b = getIntent().getExtras();
        this.nome.setText(b.getString("nome"));
        this.destino = this.formatarTelefone(b.getString("tel"));

        this.rv = findViewById(R.id.rv_chat);
        this.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        database.getReference("conversas").child(FirebaseUtils.createConversaKey(auth.getCurrentUser().getPhoneNumber(), destino)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                conversa = dataSnapshot.getValue(Conversa.class);

                if (conversa != null) {
                    if (firstMessage) {
                        mensagens = new ArrayList<>(conversa.getMensagens().values());
                        ordenarMensagensPorData();
                        adapter = new ChatAdapter(mensagens);
                        rv.setAdapter(adapter);
                        firstMessage = false;
                    } else {
                        List<Mensagem> novasMensagens = new ArrayList<>(conversa.getMensagens().values());
                        Collections.sort(novasMensagens, new Comparator<Mensagem>() {
                            @Override
                            public int compare(Mensagem o1, Mensagem o2) {
                                return o1.getDataMensagem().compareTo(o2.getDataMensagem());
                            }
                        });
                        mensagens.add(novasMensagens.get(novasMensagens.size() - 1));
                        adapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        this.mensagem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mensagem.getText().length() != 0) {
                    btnEnviar.setImageResource(R.drawable.ic_send_message);
                } else {
                    btnEnviar.setImageResource(R.drawable.ic_mic);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.enviarMensagem(mensagem.getText().toString(), destino);
                mensagem.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String formatarTelefone(String telefone) {
        if(telefone.length() != 14){
            if(telefone.length() == 9) {
                return "+55" + "61" + telefone;
            }

            if(telefone.length() == 11) {
                return "+55" + telefone;
            }
        }
        return telefone;
    }

    private void ordenarMensagensPorData() {
        Collections.sort(mensagens, new Comparator<Mensagem>() {
            @Override
            public int compare(Mensagem m1, Mensagem m2) {
                return m1.getDataMensagem().compareTo(m2.getDataMensagem());
            }
        });
    }
}
