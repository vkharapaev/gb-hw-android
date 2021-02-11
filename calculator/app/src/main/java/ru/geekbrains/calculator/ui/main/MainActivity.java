package ru.geekbrains.calculator.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import ru.geekbrains.calculator.databinding.ActivityMainBinding;
import ru.geekbrains.calculator.Settings;
import ru.geekbrains.calculator.ui.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private MainPresenter presenter;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = new Settings(this);
        changeTheme();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new MainPresenterImpl();
        initWidgets();
        presenter.takeView(this);
    }

    private void initWidgets() {
        initButtons();
    }

    private void initButtons() {
        ViewGroup buttonContainer = binding.buttonContainer;
        for (int i = 0; i < buttonContainer.getChildCount(); i++) {
            View child = buttonContainer.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(v -> {
                    Button button = (Button) v;
                    presenter.process(button.getText().toString());
                });
            }
        }

        binding.buttonSettings.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeTheme();
    }

    private void changeTheme() {
        boolean isDarkTheme = settings.isDarkTheme();
        getDelegate().setLocalNightMode(isDarkTheme ? AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void show(String text) {
        // TODO: 2/6/2021
        Log.d(TAG, "show: text = " + text);
    }
}
