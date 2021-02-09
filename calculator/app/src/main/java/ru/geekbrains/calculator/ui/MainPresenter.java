package ru.geekbrains.calculator.ui;

public interface MainPresenter {

    void takeView(MainView view);

    void process(String operation);
}
