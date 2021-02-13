package ru.geekbrains.calculator.ui.main;

import java.lang.ref.WeakReference;

import ru.geekbrains.calculator.Calculator;

public class MainPresenterImpl implements MainPresenter {

    private WeakReference<MainView> view;
    private final Calculator calculator;

    public MainPresenterImpl(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void takeView(MainView view) {
        this.view = new WeakReference<>(view);
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
