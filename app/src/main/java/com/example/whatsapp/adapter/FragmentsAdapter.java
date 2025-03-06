package com.example.whatsapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsapp.fragment.CallsFragment;
import com.example.whatsapp.fragment.ChatFragment;
import com.example.whatsapp.fragment.StatusFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {
    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new ChatFragment();
            case 1:return new StatusFragment();
            case 2:return new CallsFragment();
            default:
                new ChatFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0){
            title="CHAT";
        }else if(position==1){
            title="STATUS";
        }else if(position==2){
            title="CALLS";
        }
        return title;
    }
}
