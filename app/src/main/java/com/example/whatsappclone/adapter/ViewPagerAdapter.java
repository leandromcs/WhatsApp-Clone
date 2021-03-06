package com.example.whatsappclone.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.whatsappclone.fragment.ChamadasFragment;
import com.example.whatsappclone.fragment.ConversasFragment;
import com.example.whatsappclone.fragment.StatusFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] TABS_TITLE = { "Conversas", "Status", "Chamadas" };

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new StatusFragment();
            case 2:
                return new ChamadasFragment();
            default:
                return new ConversasFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TABS_TITLE[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
