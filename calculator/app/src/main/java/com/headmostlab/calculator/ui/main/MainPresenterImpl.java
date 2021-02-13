package com.headmostlab.calculator.ui.main;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

import com.headmostlab.calculator.calc.Calculator;

public class MainPresenterImpl extends ViewModel implements MainPresenter {

    public static final String CALCULATOR_KEY = "CALCULATOR_KEY";
    private final SavedStateHandle dataStore;
    private WeakReference<MainView> view;
    private final Calculator calculator;

    public MainPresenterImpl(SavedStateHandle savedState) {
        this.dataStore = savedState;
        Calculator restoredCalculator = savedState.get(CALCULATOR_KEY);
        this.calculator = restoredCalculator == null ? new Calculator() : restoredCalculator;
    }

    @Override
    public void takeView(MainView view) {
        this.view = new WeakReference<>(view);
        updateDisplay();
    }

    private void updateDisplay() {
        if (view() == null) {
            return;
        }
        view().show(calculator.getNumCreator().toString());
        view().showExpression(calculator.getExpCreator().toString());
    }

    @Override
    public void process(String operation) {
        if (view() == null) {
            return;
        }
        try {
            calculator.process(operation);
            dataStore.set(CALCULATOR_KEY, calculator);
            view().show(calculator.getNumCreator().toString());
        } catch (ArithmeticException e) {
            view().show(e.getMessage());
        }
        view().showExpression(calculator.getExpCreator().toString());
    }

    private MainView view() {
        return view.get();
    }
}
