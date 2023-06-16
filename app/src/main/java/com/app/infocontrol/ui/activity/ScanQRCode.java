package com.app.infocontrol.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.commons.Empleado.ResultadoQREmpleado;
import com.app.infocontrol.commons.ResultadoQRVehiculo;
import com.google.gson.Gson;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
    }

    @Override
    public void handleResult(Result result) {

        if (result.toString().length() != 0) {
            String dniEscaneado, cuitEscaneado, tipoEscaneado;

            if (result.toString().charAt(0) == '{' && result.toString().charAt(result.toString().length() - 1) == '}') {
                Gson gson = new Gson();
                ResultadoQREmpleado resultadoQREmpleado = gson.fromJson(result.toString(), ResultadoQREmpleado.class);
                dniEscaneado = resultadoQREmpleado.getDni();
                cuitEscaneado = resultadoQREmpleado.getCuit();
                tipoEscaneado = resultadoQREmpleado.getEntidad();
                if (dniEscaneado == null && cuitEscaneado != null) {
                    String dni = cuitEscaneado.substring(2, cuitEscaneado.length()-1);
                    dniEscaneado = dni;
                }
                if (dniEscaneado == null && cuitEscaneado == null) {
                    ResultadoQRVehiculo resultadoQRVehiculo = gson.fromJson(result.toString(), ResultadoQRVehiculo.class);
                    dniEscaneado = resultadoQRVehiculo.getVehiculo();
                    tipoEscaneado = resultadoQRVehiculo.getEntidad();
                }
            } else if (result.toString().contains("RUN=")) {
                int indexString = result.toString().indexOf("RUN=");
                String[] preRun = result.toString().substring(indexString).split("=");
                String run = preRun[1].replace("-", "").substring(0, preRun[1].indexOf("&type") - 1);
                dniEscaneado = run;
                tipoEscaneado = Constantes.TIPO_EMPLEADO;
                System.out.println("");
            } else {
                if (result.toString().contains("@")) {
                    String[] cadena = result.toString().split("@");
                    tipoEscaneado = Constantes.TIPO_EMPLEADO;
                    if (cadena.length > 9) {
                        dniEscaneado = cadena[1].trim();
                    } else {
                        dniEscaneado = cadena[4].trim();
                    }
                } else {
                    tipoEscaneado = Constantes.TIPO_EMPLEADO;
                    dniEscaneado = result.toString();
                }
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra(Constantes.QR_RESULT, dniEscaneado);
            resultIntent.putExtra(Constantes.QR_RESULT_TIPO, tipoEscaneado);
            setResult(RESULT_OK, resultIntent);
            onBackPressed();
        } else {

            setResult(RESULT_CANCELED, null);
            onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }
}
