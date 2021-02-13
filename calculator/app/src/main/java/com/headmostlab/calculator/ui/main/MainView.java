package com.headmostlab.calculator.ui.main;

public interface MainView {
    /**
     * Show the result of the expression
     *
     * @param text The result of the expression
     */
    void show(String text);

    /**
     * Show the expression
     *
     * @param text The expression
     */
    void showExpression(String text);
}
