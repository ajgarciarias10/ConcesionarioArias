package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.view.adapter.viewholder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.R;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Car;

public class CarViewHolder extends RecyclerView.ViewHolder{
    public TextView tvCarName, tvBrand, tvFechaDesalida, tvModelo, tvUrl;
    public ImageView ivCar;
    public Car car;
    private Bundle bundle;
    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCarName = itemView.findViewById(R.id.tvCarName);
        tvBrand = itemView.findViewById(R.id.tvBrand);
        tvFechaDesalida = itemView.findViewById(R.id.tvFechaDesalida);
        tvModelo = itemView.findViewById(R.id.tvModelo);
        tvUrl = itemView.findViewById(R.id.tvUrl);
        ivCar = itemView.findViewById(R.id.ivCar);
        itemView.setOnClickListener(v -> {
            Log.v("xyzyx", "onclick" + car.name);
            bundle = new Bundle();
            bundle.putParcelable("Concesionario", car);
            Navigation.findNavController(itemView).navigate(R.id.action_FirstFragment_to_edit_deltete,bundle);

        });

    }
}
