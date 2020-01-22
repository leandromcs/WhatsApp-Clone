package com.example.whatsappclone.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappclone.R;
import com.example.whatsappclone.fragment.AuthFirstStepFragment;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            AuthFirstStepFragment authFirstStepFragment = new AuthFirstStepFragment();

            //firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.fragment_container, authFirstStepFragment)
                                       .commit();
        }
    }
}
