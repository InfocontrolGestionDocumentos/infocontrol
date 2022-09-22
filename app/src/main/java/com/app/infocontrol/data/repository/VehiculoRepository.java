package com.app.infocontrol.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.app.infocontrol.data.room.DAO.VehiculoDAO;
import com.app.infocontrol.data.room.InfocontrolDataBase;
import com.app.infocontrol.data.room.Models.Vehiculo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class VehiculoRepository {

    private VehiculoDAO vehiculoDAO;
    private LiveData<List<Vehiculo>> allVehiculos;

    public VehiculoRepository(Application myApp){
        InfocontrolDataBase db = InfocontrolDataBase.getDatabase(myApp);
        vehiculoDAO = db.getVehiculoDao();
        allVehiculos = vehiculoDAO.getAllVehiculos();
    }

    public LiveData<List<Vehiculo>> getAllVehiculos(){return allVehiculos; }

    public void insertVehiculo(Vehiculo vehiculo){
        /*AsyncTask.execute(()-> vehiculoDAO.insert(vehiculo));*/
        vehiculoDAO.insert(vehiculo);
    }

    public List<Vehiculo> getVehiculoByValor(String valor){
        try {
            return new getVehiculoByValorAsincTask(vehiculoDAO).execute(valor).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class getVehiculoByValorAsincTask extends AsyncTask<String,Void,List<Vehiculo>>{
        private VehiculoDAO vehiculoDAO;

        getVehiculoByValorAsincTask(VehiculoDAO dao){
            vehiculoDAO = dao;
        }

        @Override
        protected List<Vehiculo> doInBackground(String... strings) {
            return vehiculoDAO.getVehiculoByValor(strings[0]);
        }
    }

    public Vehiculo getVehiculoById(String idVehiculo){
        /*try {
            return new getVehiculoByIdAsincTask(vehiculoDAO).execute(idVehiculo).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;*/
        return vehiculoDAO.getVehiculoById(idVehiculo);
    }

    private static class getVehiculoByIdAsincTask extends AsyncTask<String,Void,Vehiculo>{
        private VehiculoDAO vehiculoDAO;

        getVehiculoByIdAsincTask(VehiculoDAO dao){
            vehiculoDAO = dao;
        }

        @Override
        protected Vehiculo doInBackground(String... strings) {
            return vehiculoDAO.getVehiculoById(strings[0]);
        }
    }

    public void updateVehiculo(Vehiculo vehiculo){
        vehiculoDAO.updateVehiculo(vehiculo);
    }

    public void eliminarVehiculos(){
        AsyncTask.execute(() -> vehiculoDAO.deletAllVehiculos() );
    }
}
