package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Brand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Car;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.repository.CarRepository;

import java.util.List;

public class BrandViewModel extends AndroidViewModel {
    private CarRepository repository;
    public BrandViewModel(@NonNull Application application) {
        super(application);
        repository = new CarRepository(application);
    }
    public void insertType(Brand... brands) {
        repository.insertBrand(brands);
    }

    public void updateType(Brand... brands) {
        repository.updateBrands(brands);
    }

    public void deleteType(Brand... brands) {
        repository.deleteBrands(brands);
    }

    public LiveData<List<Brand>> getBrands() {
        return repository.getBrands();
    }

    public LiveData<Brand> getBrand(long id) {
        return repository.getBrandByID(id);
    }
}
