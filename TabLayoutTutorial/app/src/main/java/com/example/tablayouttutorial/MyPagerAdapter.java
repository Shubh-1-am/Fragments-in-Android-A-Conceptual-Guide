package com.example.tablayouttutorial;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentStateAdapter {
    public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    // List of fragments to be displayed in the view pager


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new ChatFragment();
        } else if (position == 1){
            return  new StatusFragment();
        } else
            return  new CallFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
