package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Brand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Car;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.CarBrand;

import java.util.List;
@Dao
public interface CarDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertCars(Car...cars); //INSERTANDO COCHES
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertCar(Car car); //Insertando 1 coche
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertBrands(Brand... brands); // INSERTANDO MARCAS
    @Update
    int updateCars(Car...cars); //ACTUALIZANDO COCHES
    @Update
    int updateBrands(Brand...brands);//Actualizando Marcas
    @Delete
    int deleteCars(Car...cars);//Borrando coches
    @Delete
    int deleteBrands(Brand...brands);//Borrando Marcas
    @Query("delete from carbrand")
    int deleteAllBrands();  //BORRANDO TODAS LAS MARCAS
    @Query("delete from Car")
    int deleteAllCar();  //Borrando todos los coches
    @Query("select * from car order by name asc")
    LiveData<List<Car>> getCars();//Cogiendo todos los coches
    @Query("select c.*, cb.id brand_id, cb.name brand_name from Car c join carbrand cb on c.id_brand = cb.id order by name asc")
    LiveData<List<CarBrand>> getAllCars(); //Cogiendo todos los coches y marcas
    @Query("select * from carbrand order by name asc")
    LiveData<List<Brand>> getBrands(); //Cogiendo todas las marcas
    @Query("select * from Car where id = :id")
    LiveData<Car> getCar(long id); //Cogiendo todos los coches donde sea el id que le pases
    @Query("select * from carbrand where id = :id")
    LiveData<Brand> getBrand(long id);//Cogiendo todas las marcas de coche donde sea el id que le pases
    @Query("select id from carbrand where name = :name")
    long getIdBrand(String name);
    @Query("select * from carbrand where name = :name")
    Brand getBrand(String name);









}
