package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.view.Fragments;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.R;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.databinding.FragmentEditDelteteBinding;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Brand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Car;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.viewmodel.BrandViewModel;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.viewmodel.CarViewModel;

public class edit_deltete extends Fragment {
    private Car car;
    private FragmentEditDelteteBinding binding;
    private CarViewModel pvm;
    private boolean firstTime = true;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentEditDelteteBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = new Bundle();
        bundle = getArguments();
        car = bundle.getParcelable("Concesionario");
        Log.v("xyzyx", car.name);
        binding.textInputEditCarName.setText(car.name);
        binding.textInputEditFechaDeSalida.setText(car.fechaDeSalida);
        binding.textInputEditModelo.setText(car.modelo);
        binding.textInputEditURL.setText(car.url);
        Glide.with(this).load(binding.textInputEditURL.getText().toString()).into(binding.imageView2);
        getViewModel();
        binding.btEdit.setOnClickListener(v -> {
            Car p = getCar();
            if (p.isValid()){
                pvm.updateCar(p);
                NavHostFragment.findNavController(edit_deltete.this)
                        .navigate(R.id.action_edit_deltete_to_FirstFragment);
            }
        });
        binding.btCancel.setOnClickListener(v->{
            NavHostFragment.findNavController(edit_deltete.this)
                    .navigate(R.id.action_edit_deltete_to_FirstFragment);
        });
        binding.btDelete.setOnClickListener(v->{
            Car p = getCar();
            if (p.isValid()){
                pvm.deleteCars(p);
                NavHostFragment.findNavController(edit_deltete.this)
                        .navigate(R.id.action_edit_deltete_to_FirstFragment);
            }
        });
        binding.textInputLayoutURL.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        Glide.with(this).load(binding.textInputEditURL.getText().toString()).into(binding.imageView2);

        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        Glide.with(getActivity()).load(uri).into(binding.imageView2);
                        binding.textInputEditURL.setText(uri.toString());
                    }
                });
        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");

            }
        });

    }
    private Car getCar() {
        String url = binding.textInputEditURL.getText().toString();
        String carName = binding.textInputEditCarName.getText().toString().trim();
        String  modelo = binding.textInputEditModelo.getText().toString().trim();
        String fechadesalida = binding.textInputEditFechaDeSalida.getText().toString().trim();
        Car car = new Car();
        Brand brand = (Brand) binding.spBrand.getSelectedItem();
        car.name = carName;
        car.fechaDeSalida = fechadesalida;
        car.modelo = modelo;
        car.url = url;
        car.id_brand = brand.id;
        car.id = this.car.id;
        return car;
    }

    private  void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                binding.textInputEditFechaDeSalida.setText(selectedDate);
            }
        });
    }
    private void getViewModel() {
        pvm = new ViewModelProvider(this).get(CarViewModel.class);
        pvm.getInsertResults().observe(getViewLifecycleOwner(), list -> {
            Log.v("xyzyx", "res: " + list);
            if(list.get(0) > 0) {
                if(firstTime) {
                    firstTime = false;
                } else {
                    cleanFields();
                }
            } else {
                Toast.makeText(getContext(), "10 no inserted", Toast.LENGTH_LONG).show();
            }
        });
        BrandViewModel tvm = new ViewModelProvider(this).get(BrandViewModel.class);
        tvm.getBrands().observe(getViewLifecycleOwner(), brands -> {
            Brand brand = new Brand();
            brand.id = 0;
            brand.name = getString(R.string.default_type);
            brands.add(0, brand);
            ArrayAdapter<Brand> adapter =
                    new ArrayAdapter<Brand>(getContext(), android.R.layout.simple_spinner_dropdown_item, brands);
            binding.spBrand.setAdapter(adapter);
            binding.spBrand.setSelection((int) car.id_brand);
        });
    }

    private void cleanFields() {
        binding.textInputEditURL.setText("");
        binding.textInputEditCarName.setText("");
        binding.textInputEditModelo.setText("");
        binding.textInputEditFechaDeSalida.setText("");
        binding.spBrand.setSelection(0);
        Toast.makeText(getContext(), "8 inserted", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}