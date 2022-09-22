package com.app.infocontrol.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.app.infocontrol.data.room.DAO.EmpleadoDAO;
import com.app.infocontrol.data.room.InfocontrolDataBase;
import com.app.infocontrol.data.room.Models.Empelado;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EmpleadoRepository {
    private EmpleadoDAO empleadosDao;
    private LiveData<List<Empelado>> allEmpleados;

    public EmpleadoRepository(Application myApp){
        InfocontrolDataBase db = InfocontrolDataBase.getDatabase(myApp);
        empleadosDao = db.getEmpleadoDao();
        allEmpleados = empleadosDao.getAllEmpleados();
    }

    public LiveData<List<Empelado>> getAllEmpleados(){return allEmpleados; }

    public List<Empelado> listarEmpleados(){
        try {
            return new listarEmpeladosAsyncTask(empleadosDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class listarEmpeladosAsyncTask extends  AsyncTask<Void,Void,List<Empelado>>{
        private EmpleadoDAO empleadoDAO;

        listarEmpeladosAsyncTask(EmpleadoDAO dao){
            empleadoDAO = dao;
        }

        @Override
        protected List<Empelado> doInBackground(Void... voids) {
            return empleadoDAO.listarEmpleados();
        }
    }
    public void insertEmpleado(Empelado empleado){
        /*AsyncTask.execute(()-> empleadosDao.insert(empleado));*/
        empleadosDao.insert(empleado);
    }

    public Empelado getEmpleadoById(String idEmpleado){

        return empleadosDao.getEmpleadoByIdEmpelado(idEmpleado);
    }

    public Empelado getEmpeladoByIdAsync(String idEmpleado){
        try {
            return new getEmpleadoByIdAsincTask(empleadosDao).execute(idEmpleado).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class getEmpleadoByIdAsincTask extends AsyncTask<String,Void,Empelado>{
        private EmpleadoDAO empleadoDaoById;

        getEmpleadoByIdAsincTask(EmpleadoDAO dao){
            empleadoDaoById = dao;
        }

        @Override
        protected Empelado doInBackground(String... strings) {
            return empleadoDaoById.getEmpleadoByIdEmpelado(strings[0]);
        }
    }

    public List<Empelado> getEmpleadoByDni(String dni){
        try{
            return new getEmpleadoByDniAsyncTask(empleadosDao).execute(dni).get();
        }catch ( Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static class getEmpleadoByDniAsyncTask extends AsyncTask<String, Void, List<Empelado>>{
        private EmpleadoDAO empleadoDAO;

        getEmpleadoByDniAsyncTask(EmpleadoDAO dao){ empleadoDAO = dao; }

        @Override
        protected List<Empelado> doInBackground(String... strings){
            return empleadoDAO.getEmpeladoByDni(strings[0]);
        }
    }

    public void eliminarEmpleados(){
        AsyncTask.execute(() -> empleadosDao.deletAllEmpleados() );
    }

    public void updateEmpleado(Empelado empleado){
        empleadosDao.updateEmpleado(empleado);
    }

    public List<Empelado> getEmpleadoByNfcId(String idNfc) {
        try{
            return new getEmpleadoByNfcIdAsyncTask(empleadosDao).execute(idNfc).get();
        }catch ( Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static class getEmpleadoByNfcIdAsyncTask extends AsyncTask<String, Void, List<Empelado>>{
        private EmpleadoDAO empleadoDAO;

        getEmpleadoByNfcIdAsyncTask(EmpleadoDAO dao){ empleadoDAO = dao; }

        @Override
        protected List<Empelado> doInBackground(String... strings){
            return empleadoDAO.getEmpeladoByIdNfc(strings[0]);
        }
    }
}
