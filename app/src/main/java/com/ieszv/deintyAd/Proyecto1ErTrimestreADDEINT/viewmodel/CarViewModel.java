package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Brand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Car;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.CarBrand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.repository.CarRepository;

import java.util.List;

public class CarViewModel  extends AndroidViewModel {
    private CarRepository repository;

    public CarViewModel(@NonNull Application application) {
        super(application);
        repository = new CarRepository(application);
    }

    public void insertCar(Car... cars) {
        repository.insertCars(cars);
    }

    public void updateCar(Car... cars) {
        repository.updateCar(cars);
    }

    public void deleteCars(Car... cars) {
        repository.deleteCars(cars);
    }

    public LiveData<List<Car>> getCars() {
        return repository.getCars();
    }

    public LiveData<Car> getCar(long id) {
        return repository.getCarByID(id);
    }

    public void insertCar(Car car, Brand brand) {
        repository.insertCar(car, brand);
    }

    public LiveData<List<CarBrand>> getAllCar() {
        return repository.getAllCars();
    }

    public MutableLiveData<Long> getInsertResult() {
        return repository.getInsertResult();
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return repository.getInsertResults();
    }

}
