package ru.geekbrains.calculator.ui.main;

import android.util.Log;

import java.lang.ref.WeakReference;

import ru.geekbrains.calculator.interactors.OperationProcessorInteractor;

public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();

    private WeakReference<MainView> view;
    private OperationProcessorInteractor opProcessorInteractor;

    public MainPresenterImpl(OperationProcessorInteractor interactor) {
        opProcessorInteractor = interactor;
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
            opProcessorInteractor.process(operation);
            view().show(opProcessorInteractor.getNumCreator().toString());
        } catch (ArithmeticException e) {
            view().show(e.getMessage());
        }
        view().showExpression(opProcessorInteractor.getExpCreator().toString());
    }

    private MainView view() {
        return view.get();
    }
}
