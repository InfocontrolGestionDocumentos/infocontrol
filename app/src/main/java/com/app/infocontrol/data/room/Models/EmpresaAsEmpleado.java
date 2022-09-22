package com.app.infocontrol.data.room.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.app.infocontrol.commons.Constantes;


@Entity( tableName = Constantes.TABLA_EMPRESA_AS_EMPLEADO, primaryKeys = {"idEmpresa","idEmpleado"})
public class EmpresaAsEmpleado {
    @NonNull
    private String idEmpresa;
    @NonNull
    private String idEmpleado;


    public EmpresaAsEmpleado(@NonNull String idEmpresa, @NonNull String idEmpleado) {
        this.idEmpresa = idEmpresa;
        this.idEmpleado = idEmpleado;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
