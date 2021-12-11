package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "CarBrand",
        indices = {@Index(value = "name", unique = true)})
public  class Brand {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    @Override
    public String toString() {
        return name;
    }
}
