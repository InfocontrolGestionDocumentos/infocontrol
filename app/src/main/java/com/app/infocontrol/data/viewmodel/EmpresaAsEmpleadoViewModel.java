package com.app.infocontrol.data.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.app.infocontrol.data.repository.EmpleadoRepository;
import com.app.infocontrol.data.repository.EmpresaAsEmpleadoRepository;
import com.app.infocontrol.data.room.Models.EmpresaAsEmpleado;


public class EmpresaAsEmpleadoViewModel extends AndroidViewModel {

    EmpresaAsEmpleadoRepository empresaAsEmpleadoRepository;

    private EmpleadoRepository empleadoRepository;

    public EmpresaAsEmpleadoViewModel(Application app){
        super(app);
        empresaAsEmpleadoRepository = new EmpresaAsEmpleadoRepository(app);
    }

    public void insertarEmpresaAsEmpleado(EmpresaAsEmpleado empresaAsEmpleado){
        empresaAsEmpleadoRepository.insertarEmpresaAsEmpleado(empresaAsEmpleado);
    }

    public void eliminarEmpresaAsEmpleado(){
        empresaAsEmpleadoRepository.eliminarEmpresaAsEmpleado();
    }


    public boolean existeEmpreasAsEmpelado(String idEmpresa, String idEmpleado) {

        return empresaAsEmpleadoRepository.getRelacionEmpresaAsEmpleado(idEmpresa, idEmpleado) != null;
    }

    public boolean existeEmpreasAsEmpeladoAsync(String idEmpresa, String idEmpleado) {

        return empresaAsEmpleadoRepository.getRelacionEmpresaAsEmpleadoAsync(idEmpresa, idEmpleado) != null;
    }
}
