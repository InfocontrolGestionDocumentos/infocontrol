package com.app.infocontrol.data.room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.infocontrol.data.room.Models.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Query("SELECT * FROM usuario")
    List<Usuario> getAllUsuarios();

    @Insert
    void insert(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Query("DELETE FROM usuario")
    void delete();

    @Query("SELECT * FROM usuario WHERE idUsuario = :id")
    Usuario getUsuarioById(String id);
}
