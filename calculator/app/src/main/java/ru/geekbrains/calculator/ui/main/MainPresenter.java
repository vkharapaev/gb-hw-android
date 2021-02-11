package ru.geekbrains.calculator.ui.main;

public interface MainPresenter {

    void takeView(MainView view);

    void process(String operation);
}
