package ru.geekbrains.calculator.ui.main;

import android.util.Log;

import java.lang.ref.WeakReference;

public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();

    private WeakReference<MainView> view;
    private final StringBuilder sb = new StringBuilder();

    @Override
    public void takeView(MainView view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void process(String operation) {
        // TODO: 2/6/2021  
        Log.d(TAG, "process: operation = " + operation);
        sb.append(operation);
        if (view() != null) {
            view().show(sb.toString());
        }
    }

    private MainView view() {
        return view.get();
    }
}
