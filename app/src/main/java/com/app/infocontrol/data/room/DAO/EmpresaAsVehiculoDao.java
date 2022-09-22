package com.app.infocontrol.data.room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.app.infocontrol.data.room.Models.EmpresaAsVehiculo;


@Dao
public interface EmpresaAsVehiculoDao {

    @Insert
    void insertEmpresaAsVehiculo(EmpresaAsVehiculo empresaAsVehiculo);

    @Query("DELETE FROM EmpresaAsVehiculo")
    void eliminarEmpresaAsVehiculo();

    @Query("SELECT * FROM EmpresaAsVehiculo WHERE idEmpresa = :idEmpresa AND idVehiculo = :idVehiculo")
    EmpresaAsVehiculo getRelacionEmpresaAsVehiculo(String idEmpresa, String idVehiculo);
}
