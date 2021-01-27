package ru.geekbrains.hw1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerFragmentStateAdapter extends FragmentStateAdapter {

    public PagerFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new PageFragment(Page.byPageNum(position));
    }

    @Override
    public int getItemCount() {
        return Page.values().length;
    }
}
