package com.app.infocontrol.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.app.infocontrol.data.room.DAO.ControlDeIngresoDao;
import com.app.infocontrol.data.room.InfocontrolDataBase;
import com.app.infocontrol.data.room.Models.ControlDeIngresos;

import java.util.List;

public class ControlDeIngresosRepository {

    private ControlDeIngresoDao controlDeIngresoDao;

    public ControlDeIngresosRepository(Application myApp){
        InfocontrolDataBase db = InfocontrolDataBase.getDatabase(myApp);
        controlDeIngresoDao = db.getControlDeIngresosDao();
    }

    public void insertarRegistro(ControlDeIngresos registro){
        System.out.println("insertando en db");
        AsyncTask.execute(()->controlDeIngresoDao.insert(registro));
    }

    public void actualizarRegistro(ControlDeIngresos registro){
        AsyncTask.execute(()->controlDeIngresoDao.update(registro));
    }

    public List<ControlDeIngresos> getAllRegistros(){
        try {
            return new getListadoRegistros(controlDeIngresoDao).execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ControlDeIngresos getRegistroConEntradaByIds(String idEmpleado, String idEmpresa, String salida) {
        try{
            return new getRegistroConEntradaAsync(controlDeIngresoDao,idEmpleado,idEmpresa).execute(salida).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static class getRegistroConEntradaAsync extends AsyncTask<String,Void,ControlDeIngresos>{

        private ControlDeIngresoDao dao;
        private String idEmpleado,idEmpresa;

        getRegistroConEntradaAsync(ControlDeIngresoDao dao, String idEmpleado, String idEmpresa){
            this.dao = dao;
            this.idEmpleado = idEmpleado;
            this.idEmpresa = idEmpresa;
        }

        @Override
        protected ControlDeIngresos doInBackground(String... strings) {
            return dao.getRegistroConEntradaByIds(idEmpleado,idEmpresa,strings[0]);
        }
    }

    private static class getListadoRegistros extends AsyncTask<Void,Void,List<ControlDeIngresos>>{

        private ControlDeIngresoDao controlDeIngresoDao;

        getListadoRegistros(ControlDeIngresoDao dao){
            this.controlDeIngresoDao = dao;
        }

        @Override
        protected List<ControlDeIngresos> doInBackground(Void... string) {
            return controlDeIngresoDao.getAll();
        }
    }

    public void eliminarRegistroCargado(int idControl){
        AsyncTask.execute(()->controlDeIngresoDao.deleteByIdControl(idControl));
    }

    public ControlDeIngresos getRegistroByIds(String idRegistro, String idEmpresa){
        try{
            return new getRegistroByIdAsyncTast(controlDeIngresoDao,idEmpresa).execute(idRegistro).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static class getRegistroByIdAsyncTast extends  AsyncTask<String,Void,ControlDeIngresos>{

        private String idEmpresa;
        private ControlDeIngresoDao controlDeIngresoDao;

        getRegistroByIdAsyncTast(ControlDeIngresoDao dao,String idEmpresa){
            this.controlDeIngresoDao = dao;
            this.idEmpresa = idEmpresa;
        }

        @Override
        protected ControlDeIngresos doInBackground(String... strings) {
            return controlDeIngresoDao.getRegistroByidRegistroAndidEmpresa(strings[0],idEmpresa);
        }
    }
}
