package com.headmostlab.calculator.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.headmostlab.calculator.calc.Calculator;

public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (MainPresenterImpl.class.equals(modelClass)) {
            return (T) new MainPresenterImpl(new Calculator());
        }
        throw new RuntimeException("modelClass is not supported");
    }
}
