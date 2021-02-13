package com.headmostlab.calculator.ui.main;

import java.io.Serializable;

public interface MainPresenter extends Serializable {

    void takeView(MainView view);

    void process(String operation);
}
