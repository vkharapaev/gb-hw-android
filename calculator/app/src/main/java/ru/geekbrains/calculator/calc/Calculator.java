package ru.geekbrains.calculator.calc;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import ru.geekbrains.calculator.calc.expression.Exp;
import ru.geekbrains.calculator.calc.expression.InfixExpCreator;
import ru.geekbrains.calculator.calc.number.NumCreator;

import static ru.geekbrains.calculator.calc.expression.ExpUtil.isLeftBracket;
import static ru.geekbrains.calculator.calc.expression.ExpUtil.isOperator;
import static ru.geekbrains.calculator.calc.expression.ExpUtil.isRightBracket;
import static ru.geekbrains.calculator.calc.number.NumUtil.isDigit;
import static ru.geekbrains.calculator.calc.number.NumUtil.isDot;

public class Calculator {

    private static final String OPERATION_EQ = "=";
    private static final String OPERATION_DEL = "<";
    private static final String OPERATION_CLEAR = "C";
    private static final String OPERATION_CHANGE_SIGN = "+/-";

    private final InfixExpCreator expCreator;
    private final NumCreator numCreator;
    private boolean isEqApplied = false;

    public Calculator() {
        expCreator = new InfixExpCreator();
        numCreator = new NumCreator();
    }

    public InfixExpCreator getExpCreator() {
        return expCreator;
    }

    public NumCreator getNumCreator() {
        return numCreator;
    }

    public void process(String operation) throws ArithmeticException {
        if (isDigit(operation) || isDot(operation)) {
            if (isEqApplied) {
                isEqApplied = false;
                numCreator.clear();
            }
            numCreator.add(operation);

        } else if (isOperator(operation) || isRightBracket(operation)) {
            expCreator.add(numCreator.toString());
            expCreator.add(operation);
            numCreator.clear();

        } else if (isLeftBracket(operation)) {
            expCreator.add(operation);

        } else if (OPERATION_EQ.equals(operation)) {
            isEqApplied = true;
            expCreator.add(numCreator.toString());
            numCreator.clear();
            if (!expCreator.isEmpty()) {
                List<String> postfix = Exp.convertToPostfix(expCreator.get());
                numCreator.set(convertToString(Exp.calc(postfix)));
            }

        } else if (OPERATION_DEL.equals(operation)) {
            numCreator.removeLast();

        } else if (OPERATION_CLEAR.equals(operation)) {
            numCreator.clear();
            expCreator.clear();

        } else if (OPERATION_CHANGE_SIGN.equals(operation)) {
            numCreator.changeSign();
        }
    }

    private String convertToString(BigDecimal num) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(100);
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(num);
    }
}
