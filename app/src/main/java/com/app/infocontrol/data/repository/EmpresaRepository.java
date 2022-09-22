package com.app.infocontrol.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.app.infocontrol.data.room.DAO.EmpresaDAO;
import com.app.infocontrol.data.room.InfocontrolDataBase;
import com.app.infocontrol.data.room.Models.Empresa;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EmpresaRepository {
    /*  Room    */
    private EmpresaDAO empresaDao;
    private LiveData<List<Empresa>> allEmpresas;

    public EmpresaRepository(Application myApp) {
        //  Room
        InfocontrolDataBase db = InfocontrolDataBase.getDatabase(myApp);
        empresaDao = db.getEmpresaDao();
        allEmpresas = empresaDao.getAllEmpresas();
    }

    public LiveData<List<Empresa>> getAllEmpresas() {
        return allEmpresas;
    }

    public void insertEmpresa(Empresa empresa) {
        empresaDao.insert(empresa);
        /*AsyncTask.execute(()-> empresaDao.insert(empresa));*/
    }

    public Empresa getEmpresaById(String idEmpresa) {
        return empresaDao.getEmpresaById(idEmpresa);

    }

    public Empresa getEmpresaByIdAsync(String idEmpresa) {
        try {
            return new getEmpresaByIdAsincTask(empresaDao).execute(idEmpresa).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class getEmpresaByIdAsincTask extends AsyncTask<String, Void, Empresa> {
        private EmpresaDAO empresaDaoById;

        getEmpresaByIdAsincTask(EmpresaDAO dao) {
            empresaDaoById = dao;
        }

        @Override
        protected Empresa doInBackground(String... strings) {
            return empresaDaoById.getEmpresaById(strings[0]);
        }
    }

    public void eliminarEmpresas() {
        AsyncTask.execute(() -> empresaDao.deletAllEmpresas());
    }

    public void updateEmpresa(Empresa empresa) {
        AsyncTask.execute(() -> empresaDao.updateEmpresa(empresa));
    }
}
