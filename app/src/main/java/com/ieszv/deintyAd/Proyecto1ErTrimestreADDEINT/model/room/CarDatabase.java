package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Brand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Car;

@Database(entities = {Car.class, Brand.class}, version = 1, exportSchema = false) //DEFINING THE ENTITIES OF THE DATABASE
public  abstract  class CarDatabase extends  RoomDatabase {
    private static volatile CarDatabase INSTANCE;
    /* versión simplificada */
    public static CarDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CarDatabase.class, "Concesionario").build();
        }
        return INSTANCE;
    }

    public abstract CarDAO getDao();
}
