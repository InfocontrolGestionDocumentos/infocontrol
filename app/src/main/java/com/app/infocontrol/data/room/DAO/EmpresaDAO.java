package com.app.infocontrol.data.room.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.infocontrol.data.room.Models.Empresa;

import java.util.List;


@Dao
public interface EmpresaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Empresa empresa);

    @Query("SELECT * FROM Empresa")
    LiveData<List<Empresa>> getAllEmpresas();

    @Query("SELECT * FROM Empresa WHERE idEmpresa = :id")
    Empresa getEmpresaById(String id);

    @Query("DELETE FROM Empresa")
    void deletAllEmpresas();

    @Update
    void updateEmpresa(Empresa empresa);
}
