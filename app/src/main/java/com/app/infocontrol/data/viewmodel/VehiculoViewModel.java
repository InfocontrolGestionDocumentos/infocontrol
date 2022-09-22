package com.app.infocontrol.data.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.app.infocontrol.data.repository.VehiculoRepository;
import com.app.infocontrol.data.room.Models.Vehiculo;

import java.util.List;

public class VehiculoViewModel extends AndroidViewModel {

    private LiveData<List<Vehiculo>> allVehiculos;
    private VehiculoRepository vehiculoRepository;

    public VehiculoViewModel(Application app){
        super(app);
        vehiculoRepository = new VehiculoRepository(app);
        allVehiculos = vehiculoRepository.getAllVehiculos();
    }

    public LiveData<List<Vehiculo>> getAllVehiculos(){ return allVehiculos;}

    public void insertarVehiculo(Vehiculo nuevoVehiculo) { vehiculoRepository.insertVehiculo(nuevoVehiculo);}

    public boolean existeVehiculo(String id){
        return vehiculoRepository.getVehiculoById(id) != null;
    }

    public Vehiculo getDataVehiculoById(String id){ return vehiculoRepository.getVehiculoById(id);}

    public List<Vehiculo> getDataVehiculoByValor(String valor){ return vehiculoRepository.getVehiculoByValor(valor);}

    public void eliminarVehiculos(){ vehiculoRepository.eliminarVehiculos();}

    public void updateVehiculos(Vehiculo vehiculos){ vehiculoRepository.updateVehiculo(vehiculos);}
}
