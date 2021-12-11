package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Brand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Car;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.CarBrand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.room.CarDAO;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.room.CarDatabase;

import java.util.List;

public class CarRepository {
    private static final String INIT = "init";
    private CarDAO dao;
    private LiveData<List<CarBrand>> allCar;
    private LiveData<List<Car>> liveCars;
    private LiveData<List<Brand>> liveBrands;
    private LiveData<Car> liveCar;
    private LiveData<Brand> liveBrand;
    private MutableLiveData<Long> liveInsertResult;
    private MutableLiveData<List<Long>> liveInsertResults;
    private SharedPreferences preferences;
    //region ¿COMO SE CARGA LAS LISTAS DE MARCAS PARA EL SPINNER?
            public  CarRepository(Context context){
                CarDatabase db = CarDatabase.getDatabase(context);
                dao = db.getDao();
                preferences = PreferenceManager.getDefaultSharedPreferences(context);
                liveInsertResult = new MutableLiveData<>();
                liveInsertResults = new MutableLiveData<>();
                if(!getInit()) {
                    typesPreload();
                    setInit();
                }
            }
        public boolean getInit() {
        return preferences.getBoolean(INIT, false);
    }
        //CARGAMOS LA LISTA DE MARCAS

                public void typesPreload() {
                    String[] BrandNames = new String[] {"Abarth","Alfa Romeo","Aston Martin","Audi",
                                                          "Bentley", "BMW","Cadillac","Caterham","Chevrolet","Citroen","Dacia","Ferrari","Fiat",
                                                          "Ford","Honda","Infiniti","Jaguar","Jeep","Kia","KTM","Lada","Lamborghini", "Land Rover"
                                                         ,"Land Rover","Lotus","Porsche","Renault","Seat","Volkswagen"};
                    Brand[] brands = new Brand[BrandNames.length];
                    Brand brand;
                    int cont = 0;
                    for (String s: BrandNames) {
                        brand = new Brand();
                        brand.name = s;
                        brands[cont] = brand;
                        cont++;
                    }
                    insertBrand(brands);
                }
                public void setInit() {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(INIT, true);
                    editor.commit();
                }
                public void insertBrand(Brand... brands) {
                    Runnable r = () -> {
                        dao.insertBrands(brands);
                    };
                    new Thread(r).start();
                }

     //endregion

     public void insertCars(Car... cars) {
         Runnable r = () -> {
             List<Long> resultList = dao.insertCars(cars);
             liveInsertResult.postValue(resultList.get(0));
             liveInsertResults.postValue(resultList);
         };
         new Thread(r).start();
     }

    private long insertBrandGetId(Brand brand) {
         List<Long> ids = dao.insertBrands(brand);
         if(ids.get(0) < 1) {
             return dao.getIdBrand(brand.name);
         } else {
             return ids.get(0);
         }
     }
    public void insertCar(Car car, Brand brand) {
        Runnable r = () -> {
            car.id_brand = insertBrandGetId(brand);
            if(car.id_brand > 0) {
                dao.insertCar(car);
            }
        };
        new Thread(r).start();
    }

    public void updateCar(Car...cars) {
        Runnable r = () -> {
            dao.updateCars(cars);
        };
        new Thread(r).start();
    }
    public void updateBrands(Brand...brands) {
        Runnable r = () -> {
            dao.updateBrands(brands);
        };
        new Thread(r).start();
    }
    public void deleteCars(Car...cars) {
        Runnable r = () -> {
            dao.deleteCars(cars);
        };
        new Thread(r).start();
    }
    public void deleteBrands(Brand...brands) {
        Runnable r = () -> {
            dao.deleteBrands(brands);
        };
        new Thread(r).start();
    }
    public LiveData<List<Car>> getCars() {
        if(liveCars== null) {
            liveCars = dao.getCars();
        }
        return liveCars;
    }
    public LiveData<List<Brand>> getBrands() {
        if(liveBrands == null) {
            liveBrands = dao.getBrands();
        }
        return liveBrands;
    }
    public LiveData<Car> getCarByID(long id) {
        if(liveCar == null) {
            liveCar = dao.getCar(id);
        }
        return liveCar;
    }
    public LiveData<Brand> getBrandByID(long id) {
        if(liveBrand == null) {
            liveBrand = dao.getBrand(id);
        }
        return liveBrand;
    }
    public LiveData<List<CarBrand>> getAllCars() {
        if(allCar == null) {
            allCar = dao.getAllCars();
        }
        return allCar;
    }
    public MutableLiveData<Long> getInsertResult() {
        return liveInsertResult;
    }
    public MutableLiveData<List<Long>> getInsertResults() {
        return liveInsertResults;
    }






}
