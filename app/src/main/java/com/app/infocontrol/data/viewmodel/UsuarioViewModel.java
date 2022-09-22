package com.app.infocontrol.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.infocontrol.data.repository.UsuarioRepository;
import com.app.infocontrol.data.room.Models.Usuario;

import java.util.List;


public class UsuarioViewModel extends AndroidViewModel {

    private UsuarioRepository usuarioRepository;

    public UsuarioViewModel(@NonNull Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
    }
    /*Obtenemos los usuarios*/

    public List<Usuario> getUsuarios(){
        return usuarioRepository.getUsuarios();
    }

    public void inserUser(Usuario usuario){
        usuarioRepository.insertUsuario(usuario);
    }

    public void updateUser(Usuario usuario){
        usuarioRepository.updateUser(usuario);
    }

    public void deleteAll(){
        usuarioRepository.deleteAll();
    }

    public Usuario getUserById( String idUsuario){
        return usuarioRepository.getUsuarioById(idUsuario);
    }

    public boolean existe(String id){
        return usuarioRepository.getUsuarioById(id) != null;
    }
}
