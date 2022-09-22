package com.app.infocontrol.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.app.infocontrol.data.room.DAO.EmpresaAsVehiculoDao;
import com.app.infocontrol.data.room.InfocontrolDataBase;
import com.app.infocontrol.data.room.Models.EmpresaAsVehiculo;

import java.util.concurrent.ExecutionException;

public class EmpresaAsVehiculoRepository {

    private EmpresaAsVehiculoDao empresaAsVehiculoDao;

    public EmpresaAsVehiculoRepository(Application myApp){
        InfocontrolDataBase db = InfocontrolDataBase.getDatabase(myApp);
        empresaAsVehiculoDao = db.getEmpresaAsVehiculoDao();

    }

    public void insertarEmpresaAsVehiculo(EmpresaAsVehiculo empresaAsVehiculo){
        /*AsyncTask.execute(() -> empresaAsVehiculoDao.insertEmpresaAsVehiculo(empresaAsVehiculo));*/
        empresaAsVehiculoDao.insertEmpresaAsVehiculo(empresaAsVehiculo);
    }

    public void eliminarEmpresaAsVehiculo(){
        AsyncTask.execute(()-> empresaAsVehiculoDao.eliminarEmpresaAsVehiculo());
    }

    public EmpresaAsVehiculo getRelacionEmpresaAsVehiculo(String idEmpresa,String idVehiculo){
        return empresaAsVehiculoDao.getRelacionEmpresaAsVehiculo(idEmpresa,idVehiculo);
    }

    public EmpresaAsVehiculo getRelacionEmpresaAsVehiculoAsync(String idEmpresa, String idVehiculo) {
        try {
            return new getRelacionEmpresaAsVehiculoAsyncTask(empresaAsVehiculoDao,idEmpresa).execute(idVehiculo).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class getRelacionEmpresaAsVehiculoAsyncTask extends AsyncTask<String,Void,EmpresaAsVehiculo>{
        private EmpresaAsVehiculoDao empresaAsVehiculoDao1;
        private String idEmpresa;

        getRelacionEmpresaAsVehiculoAsyncTask(EmpresaAsVehiculoDao dao,String idEmpresa){
            this.empresaAsVehiculoDao1 = dao;
            this.idEmpresa = idEmpresa;
        }

        @Override
        protected EmpresaAsVehiculo doInBackground(String... strings) {
            return empresaAsVehiculoDao1.getRelacionEmpresaAsVehiculo(idEmpresa,strings[0]);
        }
    }

}
