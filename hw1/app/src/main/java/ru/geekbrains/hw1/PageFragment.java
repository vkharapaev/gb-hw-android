package ru.geekbrains.hw1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.geekbrains.hw1.databinding.FragmentPage1Binding;
import ru.geekbrains.hw1.databinding.FragmentPage2Binding;
import ru.geekbrains.hw1.databinding.FragmentPage3Binding;

public class PageFragment extends Fragment {
    private final Page page;

    public PageFragment(Page page) {
        this.page = page;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (page) {
            case FIRST:
                return FragmentPage1Binding.inflate(inflater).getRoot();
            case SECOND:
                return FragmentPage2Binding.inflate(inflater).getRoot();
            case THIRD:
                return FragmentPage3Binding.inflate(inflater).getRoot();
        }
        return null;
    }
}
