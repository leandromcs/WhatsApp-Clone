package com.example.whatsappclone.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.whatsappclone.R;
import com.example.whatsappclone.model.Conversa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private FloatingActionButton btnEnviar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.chat_toolbar));

        this.btnEnviar = findViewById(R.id.btn_enviar);
        this.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensagem();
            }
        });
    }

    private void enviarMensagem() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String myRef = database.getReference("conversas").push().getKey();
        DatabaseReference myRef2 = database.getReference("conversas").child(myRef);
        Map<String, String> participantes = new HashMap<>();
        participantes.put("Leandro", "Leandro");
        participantes.put("Robô", "Robô");
        Conversa conversa = new Conversa(participantes);
        myRef2.setValue(conversa);
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
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
