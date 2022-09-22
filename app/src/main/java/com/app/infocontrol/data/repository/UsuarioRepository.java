package com.app.infocontrol.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.app.infocontrol.data.room.DAO.UsuarioDAO;
import com.app.infocontrol.data.room.InfocontrolDataBase;
import com.app.infocontrol.data.room.Models.Usuario;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UsuarioRepository {
    private UsuarioDAO usuarioDao;

    public UsuarioRepository(Application myApp){
        InfocontrolDataBase db = InfocontrolDataBase.getDatabase(myApp);
        usuarioDao = db.getUserDataDao();
    }

    /*Obtener todos los usuarios (Seria solo uno ya que es el unico usuario almacenado)*/
    public List<Usuario> getUsuarios(){
        try {
            return new listarUsuarios(usuarioDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class listarUsuarios extends  AsyncTask<Void,Void,List<Usuario>>{
        private UsuarioDAO usuarioDao;

        listarUsuarios(UsuarioDAO dao){
            usuarioDao = dao;
        }

        @Override
        protected List<Usuario> doInBackground(Void... voids) {
            return usuarioDao.getAllUsuarios();
        }
    }

    public void insertUsuario(Usuario usuario){
        AsyncTask.execute(()-> usuarioDao.insert(usuario));
    }

    public Usuario getUsuarioById(String idUsuario){
        try {
            return new getUSuarioByIdAsincTask(usuarioDao).execute(idUsuario).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class getUSuarioByIdAsincTask extends AsyncTask<String,Void,Usuario>{
        private UsuarioDAO usuarioDAO;

        getUSuarioByIdAsincTask(UsuarioDAO dao){
            usuarioDAO = dao;
        }

        @Override
        protected Usuario doInBackground(String... strings) {
            return usuarioDAO.getUsuarioById(strings[0]);
        }
    }

    public void deleteAll(){
        AsyncTask.execute(() -> usuarioDao.delete());
    }

    public void updateUser(Usuario usuario) {
        AsyncTask.execute(() -> usuarioDao.update(usuario));
    }
}
