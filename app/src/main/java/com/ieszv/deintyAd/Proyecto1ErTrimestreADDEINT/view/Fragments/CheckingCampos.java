package com.ieszv.deintyAd.Proyecto1ErTrimestreADDEINT.view.Fragments;

import com.google.android.material.textfield.TextInputEditText;

public class CheckingCampos {
    public  static   boolean checkingEMPTYFIELDs (TextInputEditText editalo){
        if(editalo.getText().toString().isEmpty() || editalo.getText().toString() == null){
            editalo.setError("Error debes rellenar el campo de texto");
            return false;
        }
        else{
            return true;
        }
    }
}
