package com.app.infocontrol.ui.fragment.dialogs;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.infocontrol.R;
import com.app.infocontrol.worker.ServicesManager;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingDialogFragment extends DialogFragment {

    //Variables de interfaz
    private TextView tvDescripcion;
    private ProgressBar pbProgreso;
    private MaterialButton btnCancelar;

    public LoadingDialogFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading_dialog, container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        /*  Inicializo el Fragment y mando por parametro las variables bindeadas a la interfaz*/
        inicializarVariables(view);
        tvDescripcion.setText("Conectando al servidor");
        btnCancelar.setEnabled(true);
        /*obtenerDatosDeLasEmpresas();*/
        ServicesManager.loadingTask(tvDescripcion,pbProgreso,btnCancelar,this);
        btnCancelar.setOnClickListener(v -> {
            dismiss();
        });
        return view;
    }

    private void inicializarVariables(View view) {

        /*  Variables de interfaz*/
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        pbProgreso = view.findViewById(R.id.pbProgreso);
        btnCancelar = view.findViewById(R.id.btnCancelar);

    }

}
