package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.R;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Brand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Car;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.CarBrand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.view.adapter.viewholder.CarViewHolder;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder>{
    private List<CarBrand> CarList;
    private Context context;
    public CarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return  new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        CarBrand pokemonType = CarList.get(position);
        Car car = pokemonType.car;
        holder.car = car;
        Brand brand = pokemonType.brand;
        holder.tvUrl.setText(car.url);
        holder.tvModelo.setText(car.modelo);
        holder.tvFechaDesalida.setText(car.fechaDeSalida );
        holder.tvBrand.setText(brand.name);
        holder.tvCarName.setText(car.name);
        Glide.with(context).load(car.url).into(holder.ivCar);
    }


    @Override
    public int getItemCount() {
        if(CarList == null) {
            return 0;
        }
        return CarList.size();
    }
    public void setCarList(List<CarBrand> carList) {

        this.CarList = carList;
        notifyDataSetChanged();
    }
}
