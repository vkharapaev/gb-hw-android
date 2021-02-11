package ru.geekbrains.calculator.interactors;

import ru.geekbrains.calculator.expression.InfixExpCreator;
import ru.geekbrains.calculator.number.NumCreator;

public interface OperationProcessorInteractor {
    void process(String operator);

    InfixExpCreator getExpCreator();

    NumCreator getNumCreator();
}
