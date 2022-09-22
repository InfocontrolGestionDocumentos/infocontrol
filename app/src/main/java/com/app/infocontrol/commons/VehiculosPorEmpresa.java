package com.app.infocontrol.commons;



import com.app.infocontrol.data.pojo.response.ResponseVehiculos.DataVehiculos;

import java.util.List;

public class VehiculosPorEmpresa {

    private String idEmpresa;
    private List<DataVehiculos> vehiculos;

    public VehiculosPorEmpresa() {
    }

    public VehiculosPorEmpresa(String idEmpresa, List<DataVehiculos> vehiculos) {
        this.idEmpresa = idEmpresa;
        this.vehiculos = vehiculos;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<DataVehiculos> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<DataVehiculos> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
