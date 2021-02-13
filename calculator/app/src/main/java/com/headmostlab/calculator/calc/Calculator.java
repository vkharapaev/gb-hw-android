package com.headmostlab.calculator.calc;

import android.os.Parcel;
import android.os.Parcelable;

import com.headmostlab.calculator.calc.expression.Exp;
import com.headmostlab.calculator.calc.expression.InfixExpCreator;
import com.headmostlab.calculator.calc.number.NumCreator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static com.headmostlab.calculator.calc.expression.ExpUtil.isLeftBracket;
import static com.headmostlab.calculator.calc.expression.ExpUtil.isOperator;
import static com.headmostlab.calculator.calc.expression.ExpUtil.isRightBracket;
import static com.headmostlab.calculator.calc.number.NumUtil.isDigit;
import static com.headmostlab.calculator.calc.number.NumUtil.isDot;

public class Calculator implements Parcelable {

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

    public Calculator(Parcel src) {
        this();
        numCreator.set(src.readString());
        expCreator.set(src.readString());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numCreator.toString());
        dest.writeString(expCreator.toString());
    }

    public static final Parcelable.Creator<Calculator> CREATOR = new Parcelable.Creator<Calculator>() {

        @Override
        public Calculator createFromParcel(Parcel source) {
            return new Calculator(source);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };
}
