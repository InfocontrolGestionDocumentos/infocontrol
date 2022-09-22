package com.app.infocontrol.data.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.app.infocontrol.data.repository.EmpleadoRepository;
import com.app.infocontrol.data.room.Models.Empelado;

import java.util.List;

public class EmpleadoViewModel extends AndroidViewModel {
    private LiveData<List<Empelado>> allEmpleados;
    private EmpleadoRepository empleadoRepository;

    public EmpleadoViewModel(Application app){
        super(app);
        empleadoRepository = new EmpleadoRepository(app);
        allEmpleados = empleadoRepository.getAllEmpleados();
    }

    //El fragmento que necesita el listado de datos de los docentes, en este caso PersonasListFragment
    public LiveData<List<Empelado>> getAllEmpleados(){ return allEmpleados;}

    public List<Empelado> listarEmpleados(){ return empleadoRepository.listarEmpleados(); }
    //metodo para insertar el nuevo elemento
    public void insertarEmpleado(Empelado nuevoEmpleado) { empleadoRepository.insertEmpleado(nuevoEmpleado);}

    public boolean existeEmpleado(String id){
        return empleadoRepository.getEmpleadoById(id) != null;
    }

    public Empelado getDataEmpleadoById(String id){ return empleadoRepository.getEmpeladoByIdAsync(id);}

    public List<Empelado> getEmpleadoByDni(String dni){ return empleadoRepository.getEmpleadoByDni(dni);}

    public List<Empelado> getEmpleadoByNfcId(String idNfc){ return empleadoRepository.getEmpleadoByNfcId(idNfc);}

    public void eliminarEmpleados(){ empleadoRepository.eliminarEmpleados();}

    public void updateEmpleado(Empelado empleado){ empleadoRepository.updateEmpleado(empleado);}
}