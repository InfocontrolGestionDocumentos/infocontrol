package com.app.infocontrol.data.room.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.app.infocontrol.commons.Constantes;


@Entity(tableName = Constantes.TABLA_EMPRESA_AS_VEHICULO, primaryKeys = {"idEmpresa","idVehiculo"})
public class EmpresaAsVehiculo{

    @NonNull
    private String idEmpresa;
    @NonNull
    private String idVehiculo;

    public EmpresaAsVehiculo(@NonNull String idEmpresa, @NonNull String idVehiculo) {
        this.idEmpresa = idEmpresa;
        this.idVehiculo = idVehiculo;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
}
