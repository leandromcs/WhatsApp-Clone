package com.example.whatsappclone.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappclone.R;
import com.example.whatsappclone.fragment.AuthFirstStepFragment;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int auth = sharedPref.getInt(getString(R.string.phone_auth_preferences), 0);

        if(auth == 1) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            AuthFirstStepFragment authFirstStepFragment = new AuthFirstStepFragment();

            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.fragment_container, authFirstStepFragment)
                                       .commit();
        }
    }

}
