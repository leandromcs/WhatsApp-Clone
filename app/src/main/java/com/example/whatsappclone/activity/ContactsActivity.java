package com.example.whatsappclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.whatsappclone.R;
import com.example.whatsappclone.adapter.ContactsAdapter;
import com.example.whatsappclone.model.Contato;
import com.example.whatsappclone.utils.FirebaseUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private List<Contato> contatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            contatos = FirebaseUtils.getContactList(getContentResolver());
            this.ordernarContatoPorOrdemAlfabetica();
        }

        setSupportActionBar((Toolbar) findViewById(R.id.contacts_toolbar));

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        RecyclerView rv = findViewById(R.id.contacts_rv);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        rv.setAdapter(new ContactsAdapter(contatos));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts_menu, menu);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            contatos = FirebaseUtils.getContactList(getContentResolver());
            this.ordernarContatoPorOrdemAlfabetica();
        }
    }

    private void ordernarContatoPorOrdemAlfabetica() {
        Collections.sort(contatos, new Comparator<Contato>(){
            @Override
            public int compare(Contato o1, Contato o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });
    }
}
