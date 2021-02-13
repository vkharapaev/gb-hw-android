package com.headmostlab.calculator.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import com.headmostlab.calculator.Settings;
import com.headmostlab.calculator.databinding.ActivityMainBinding;
import com.headmostlab.calculator.ui.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String PRESENTER_KEY = "presenter";
    private ActivityMainBinding binding;
    private MainPresenter presenter;
    private Settings settings;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        settings = new Settings(this);
        changeTheme();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ViewModelProvider(this, new ViewModelFactory(this, null))
                .get(MainPresenterImpl.class);
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
                Button button = (Button) child;
                String text = button.getText().toString();
                if (text.equals("()")) {
                    button.setOnClickListener((view) -> {
                        presenter.process(")");
                        presenter.process("(");
                    });
                } else {
                    button.setOnClickListener((view) -> presenter.process(text));
                }
            }
        }

        if (binding.buttonSettings != null) {
            binding.buttonSettings.setOnClickListener(v ->
                    startActivity(new Intent(this, SettingsActivity.class)));
        }
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
        binding.numberDisplay.setText(text);
    }

    @Override
    public void showExpression(String text) {
        binding.expressionDisplay.setText(text);
    }
}
