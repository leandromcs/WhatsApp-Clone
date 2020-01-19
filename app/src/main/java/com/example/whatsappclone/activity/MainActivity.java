package com.example.whatsappclone.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.whatsappclone.R;
import com.example.whatsappclone.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private TabLayout tb;
    private ViewPager vw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.app_toolbar));

        this.vw = findViewById(R.id.pager);
        this.tb = findViewById(R.id.tabLayout);

        this.vw.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        this.tb.setupWithViewPager(this.vw);
    }

    @Override
    public void onBackPressed() {
        if (vw.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            vw.setCurrentItem(vw.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
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