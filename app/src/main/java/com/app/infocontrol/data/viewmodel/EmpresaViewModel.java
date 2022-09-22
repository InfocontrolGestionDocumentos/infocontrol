package com.app.infocontrol.data.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.app.infocontrol.data.repository.EmpresaRepository;
import com.app.infocontrol.data.room.Models.Empresa;

import java.util.List;

public class EmpresaViewModel extends AndroidViewModel {
    private LiveData<List<Empresa>> allEmpresas;
    private EmpresaRepository empresaRepository;

    public EmpresaViewModel(Application app){
        super(app);
        empresaRepository = new EmpresaRepository(app);
        allEmpresas = empresaRepository.getAllEmpresas();
    }

    //El fragmento que necesita el listado de datos de los docentes, en este caso PersonasListFragment
    public LiveData<List<Empresa>> getAllEmpresas(){ return allEmpresas;}

    public Empresa getEmpresaById(String idEmpresa){
        return empresaRepository.getEmpresaById(idEmpresa);
    }

    public Empresa getEmpresaByIdAsync(String idEmpresa){
        return empresaRepository.getEmpresaByIdAsync(idEmpresa);
    }

    //metodo para insertar el nuevo elemento
    public void insertarEmpresa(Empresa nuevaEmpresa) { empresaRepository.insertEmpresa(nuevaEmpresa);}

    public boolean existeEmpresa(String id){
        return empresaRepository.getEmpresaById(id) != null;
    }

    public boolean existeEmpresaAsync(String idEmpresa){
        return empresaRepository.getEmpresaByIdAsync(idEmpresa) != null;
    }

    public void eliminarEmpresas(){ empresaRepository.eliminarEmpresas();}

    public void updateEmpresa(Empresa empresa) { empresaRepository.updateEmpresa(empresa);}
}
