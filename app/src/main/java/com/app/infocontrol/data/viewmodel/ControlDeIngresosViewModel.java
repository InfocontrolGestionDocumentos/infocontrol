package com.app.infocontrol.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.app.infocontrol.data.repository.ControlDeIngresosRepository;
import com.app.infocontrol.data.room.Models.ControlDeIngresos;

import java.util.List;

public class ControlDeIngresosViewModel extends AndroidViewModel {

    private ControlDeIngresosRepository controlDeIngresosRepository;

    public ControlDeIngresosViewModel(@NonNull Application application) {
        super(application);
        controlDeIngresosRepository = new ControlDeIngresosRepository(application);
    }

    public void insertarRegistro(ControlDeIngresos registro){
        controlDeIngresosRepository.insertarRegistro(registro);
    }

    public void updateRegistro(ControlDeIngresos registro){
        controlDeIngresosRepository.actualizarRegistro(registro);
    }

    public List<ControlDeIngresos> getAllRegistros(){
        return controlDeIngresosRepository.getAllRegistros();
    }

    public ControlDeIngresos getRegistro(String idRegistro,String idEmpresa){
        return controlDeIngresosRepository.getRegistroByIds(idRegistro,idEmpresa);
    }

    public void eliminarRegistroById(int idControl){
        controlDeIngresosRepository.eliminarRegistroCargado(idControl);
    }

    public ControlDeIngresos getRegistroConEntrada(String idEmpleado, String idEmpresa, String salida) {
        return controlDeIngresosRepository.getRegistroConEntradaByIds(idEmpleado,idEmpresa,salida);
    }
}
