package com.headmostlab.calculator.ui.main;

import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

import com.headmostlab.calculator.calc.Calculator;

public class MainPresenterImpl extends ViewModel implements MainPresenter {

    private WeakReference<MainView> view;
    private final Calculator calculator;

    public MainPresenterImpl(Calculator calculator) {
        this.calculator = calculator;
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
