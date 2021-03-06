package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.view.Fragments;

import static com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.view.Fragments.CheckingCampos.checkingEMPTYFIELDs;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.R;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.databinding.FragmentAddBinding;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Brand;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.model.entity.Car;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.viewmodel.BrandViewModel;
import com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.viewmodel.CarViewModel;

import java.util.Objects;

public class ADDFragment extends Fragment {
    private Car car;
    private CarViewModel pvm;
    private boolean firstTime = true;
    private FragmentAddBinding binding;
    private static final int SELECT_IMAGE = 200;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel();
        defineAddListener();
        binding.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ADDFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        //PARA EL ICONO FINAL DE LA FECHA
        binding.textInputLayouFechadesalida.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        Glide.with(getActivity()).load(uri).into(binding.imageView2);
                        binding.textInputEditURL.setText(uri.toString());
                    }
                });



        binding.textInputLayoutURL.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });
        Glide.with(this).load(Objects.requireNonNull(binding.textInputEditURL.getText()).toString()).into(binding.imageView2);

    }


    private void defineAddListener() {
        binding.btEdit.setOnClickListener(v -> {
            if(checkingEMPTYFIELDs(binding.textInputEditCarName)||checkingEMPTYFIELDs(binding.textInputEditModelo) || checkingEMPTYFIELDs(binding.textInputEditURL) || checkingEMPTYFIELDs(binding.textInputEditFechaDeSalida)){
                Car car = getCar();
                if(car.isValid()) {
                    addCar(car);
                    NavHostFragment.findNavController(ADDFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                } else {
                    Toast.makeText(getContext(), "Car is  no valid", Toast.LENGTH_LONG).show();
                }
            }

            //1?? validar los datos
            //2?? si est?? bien
            //   3?? insertar
            //   4?? si he insertado bien
            //      5?? si es la primera vez
            //         6?? mostrar el alert
            //      7?? sino
            //         8?? limpiar + toast
            //   9?? sino
            //      10?? toast
            //11?? sino
            //   12?? toast
        });
    }
    @NonNull
    private Car getCar() {
        String CarName = binding.textInputEditCarName.getText().toString().trim();
        String modelo = binding.textInputEditModelo.getText().toString().trim();
        String fechadesalida = binding.textInputEditFechaDeSalida.getText().toString().trim();
        String url = binding.textInputEditURL.getText().toString().trim();
        Brand brand = (Brand) binding.spBrand.getSelectedItem();
        Car car = new Car();
        car.name = CarName;
        car.modelo  = modelo;
        car.fechaDeSalida= fechadesalida;
        car.url = url;
        car.id_brand = brand.id;
        return car;
    }
    private  void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                binding.textInputEditFechaDeSalida.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    private void addCar(Car car) {
        pvm.insertCar(car);
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
                Toast.makeText(getContext(), "No se ha insertado", Toast.LENGTH_LONG).show();
            }
        });

        BrandViewModel tvm = new ViewModelProvider(this).get(BrandViewModel.class);
        tvm.getBrands().observe(getViewLifecycleOwner(), types -> {
            Brand brand = new Brand();
            brand.id = 0;
            brand.name = getString(R.string.default_type);
            types.add(0, brand);
            ArrayAdapter<Brand> adapter =
                    new ArrayAdapter<Brand>(getContext(), android.R.layout.simple_spinner_dropdown_item, types);
            binding.spBrand.setAdapter(adapter);
        });
    }
    private void cleanFields() {
        binding.textInputEditCarName.setText("");
        binding.textInputEditModelo.setText("");
        binding.textInputEditFechaDeSalida.setText("");
        binding.textInputEditURL.setText("");
        binding.spBrand.setSelection(0);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    }



