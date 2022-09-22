package com.app.infocontrol.commons;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class VerificarEstadoDeConexion {

    public static boolean isNetDisponible(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }
}
