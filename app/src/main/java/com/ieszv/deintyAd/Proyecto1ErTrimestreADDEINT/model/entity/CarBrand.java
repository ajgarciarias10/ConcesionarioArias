package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity;

import androidx.room.Embedded;

public class CarBrand {
    @Embedded
    public Car car;
    @Embedded(prefix="brand_")
    public Brand brand;
}
