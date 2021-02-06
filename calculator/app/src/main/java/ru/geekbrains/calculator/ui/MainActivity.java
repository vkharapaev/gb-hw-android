package ru.geekbrains.calculator.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ru.geekbrains.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

    @Override
    public void show(String text) {
        // TODO: 2/6/2021
        Log.d(TAG, "show: text = " + text);
    }
}
