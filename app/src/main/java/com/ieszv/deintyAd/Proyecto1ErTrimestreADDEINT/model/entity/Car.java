package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Car",
        indices = {@Index(value = "name", unique = true)},
        foreignKeys = {@ForeignKey(entity = Brand.class, parentColumns = "id", childColumns = "id_brand", onDelete = ForeignKey.CASCADE)})
public class Car implements Parcelable{
    //id autonumérico
    @PrimaryKey(autoGenerate = true)
    public long id;  //ID  DEL COCHESITO
    @NonNull
    @ColumnInfo(name = "name")
    public String name; // NOMBRE DEL COCHESITO
    @NonNull
    @ColumnInfo(name = "fechaDeSalida")
    public String fechaDeSalida; // Fecha de salida
    @NonNull
    @ColumnInfo(name = "id_brand")
    public long id_brand;
    @ColumnInfo(name = "modelo")
    public String modelo; //MODELO
    @ColumnInfo(name = "url")
    public String url;  // URL
    public Car() {
    }


    protected Car(Parcel in) {
        id = in.readLong();
        name = in.readString();
        fechaDeSalida = in.readString();
        id_brand = in.readLong();
        modelo = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(fechaDeSalida);
        dest.writeLong(id_brand);
        dest.writeString(modelo);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public boolean isValid() {
        return !(name.isEmpty() || modelo.isEmpty()  || fechaDeSalida.isEmpty() || url.isEmpty() || id_brand <= 0);
        //shortcut
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}