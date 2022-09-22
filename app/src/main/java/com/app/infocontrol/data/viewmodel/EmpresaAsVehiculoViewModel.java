package com.app.infocontrol.data.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.app.infocontrol.data.repository.EmpresaAsVehiculoRepository;
import com.app.infocontrol.data.room.Models.EmpresaAsVehiculo;


public class EmpresaAsVehiculoViewModel extends AndroidViewModel {

    private EmpresaAsVehiculoRepository empresaAsVehiculoRepository;

    public EmpresaAsVehiculoViewModel(Application myApp){
        super(myApp);
        empresaAsVehiculoRepository = new EmpresaAsVehiculoRepository(myApp);
    }

    public void insertarEmpresaAsVehiculo(EmpresaAsVehiculo empresaAsVehiculo){
        empresaAsVehiculoRepository.insertarEmpresaAsVehiculo(empresaAsVehiculo);
    }

    public void eliminarEmpresaAsVehiculo(){
        empresaAsVehiculoRepository.eliminarEmpresaAsVehiculo();
    }


    public boolean existeRelacion(String idEmpresa, String idVehiculo) {
        return empresaAsVehiculoRepository.getRelacionEmpresaAsVehiculo(idEmpresa, idVehiculo) != null;
    }

    public boolean existeRelacionAsync(String idEmpresa, String idVehiculo) {
        return empresaAsVehiculoRepository.getRelacionEmpresaAsVehiculoAsync(idEmpresa, idVehiculo) != null;
    }


}
