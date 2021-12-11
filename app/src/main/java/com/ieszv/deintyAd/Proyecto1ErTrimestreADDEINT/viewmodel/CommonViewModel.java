package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.repository.CarRepository;

public class CommonViewModel extends ViewModel {
    private Context context;
    private CarRepository repository;
    public CommonViewModel() {
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
