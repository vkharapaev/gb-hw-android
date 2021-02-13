package com.headmostlab.calculator.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.savedstate.SavedStateRegistryOwner;

public class ViewModelFactory extends AbstractSavedStateViewModelFactory implements ViewModelProvider.Factory {

    public ViewModelFactory(@NonNull SavedStateRegistryOwner owner, @Nullable Bundle defaultArgs) {
        super(owner, defaultArgs);
    }

    @NonNull
    @Override
    protected <T extends ViewModel> T create(@NonNull String key, @NonNull Class<T> modelClass, @NonNull SavedStateHandle handle) {
        if (MainPresenterImpl.class.equals(modelClass)) {
            return (T) new MainPresenterImpl(handle);
        }
        throw new RuntimeException("modelClass is not supported");
    }
}
