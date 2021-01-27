package ru.geekbrains.hw1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import ru.geekbrains.hw1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        ui.pager.setAdapter(new PagerFragmentStateAdapter(this));

        ui.pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                ui.bottomNav.setSelectedItemId(Page.byPageNum(position).menuId);
            }
        });

        ui.bottomNav.setOnNavigationItemSelectedListener(item -> {
            ui.pager.setCurrentItem(Page.byMenuId(item.getItemId()).pageNum);
            return true;
        });

    }
}