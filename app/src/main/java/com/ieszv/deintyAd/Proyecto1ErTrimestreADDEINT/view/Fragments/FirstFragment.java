package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.view.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.R;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.databinding.FragmentFirstBinding;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.CarBrand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.view.adapter.CarAdapter;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.viewmodel.CarViewModel;

import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //lista de pokemon
        RecyclerView rvCar = binding.rvCar;
        rvCar.setLayoutManager(new LinearLayoutManager(getContext()));
        CarViewModel pvm = new ViewModelProvider(getActivity()).get(CarViewModel.class);
        CarAdapter carAdapter = new CarAdapter(getContext());
        rvCar.setAdapter(carAdapter);
        LiveData<List<CarBrand>> listaPokemonType = pvm.getAllCar();
        listaPokemonType.observe(getViewLifecycleOwner(), carAdapter::setCarList);

        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}