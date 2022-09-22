package com.app.infocontrol.data.repository;

import android.app.Application;
import android.os.AsyncTask;


import com.app.infocontrol.data.room.DAO.EmpresaAsEmpleadoDao;
import com.app.infocontrol.data.room.InfocontrolDataBase;
import com.app.infocontrol.data.room.Models.EmpresaAsEmpleado;

import java.util.concurrent.ExecutionException;

public class EmpresaAsEmpleadoRepository {

    private EmpresaAsEmpleadoDao empresaAsEmpleadoDao;

    public EmpresaAsEmpleadoRepository(Application myApp) {
        InfocontrolDataBase db = InfocontrolDataBase.getDatabase(myApp);
        empresaAsEmpleadoDao = db.getEmpresaAsEmpleadoDao();
    }

    public void insertarEmpresaAsEmpleado(EmpresaAsEmpleado empresaAsEmpleado){
        /*AsyncTask.execute(()-> empresaAsEmpleadoDao.insertarEmpresaAsEmpleado(empresaAsEmpleado));*/
        empresaAsEmpleadoDao.insertarEmpresaAsEmpleado(empresaAsEmpleado);
    }

    public void eliminarEmpresaAsEmpleado(){
        AsyncTask.execute(()-> empresaAsEmpleadoDao.eliminarEmpresaAsEmpleados());
    }

    public EmpresaAsEmpleado getRelacionEmpresaAsEmpleado(String idEmpresa,String idEmpleado){
        return empresaAsEmpleadoDao.getRelacionEmpresaAsEmpleado(idEmpresa,idEmpleado);
    }

    public EmpresaAsEmpleado getRelacionEmpresaAsEmpleadoAsync(String idEmpresa,String idEmpleado){
        try {
            return new getRelacionEmpresaAsEmpleadoAsyncTask(empresaAsEmpleadoDao,idEmpresa).execute(idEmpleado).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class getRelacionEmpresaAsEmpleadoAsyncTask extends AsyncTask<String,Void, EmpresaAsEmpleado>{
        private EmpresaAsEmpleadoDao empresaAsEmpleadoDao1;
        private String idEmpresa;

        getRelacionEmpresaAsEmpleadoAsyncTask(EmpresaAsEmpleadoDao dao,String idEmpresa){
            this.empresaAsEmpleadoDao1 = dao;
            this.idEmpresa = idEmpresa;
        }

        @Override
        protected EmpresaAsEmpleado doInBackground(String... strings) {
            return empresaAsEmpleadoDao1.getRelacionEmpresaAsEmpleado(idEmpresa,strings[0]);
        }
    }

}

