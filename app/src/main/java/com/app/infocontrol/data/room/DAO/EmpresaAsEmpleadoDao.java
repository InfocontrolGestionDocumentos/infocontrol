package com.app.infocontrol.data.room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.app.infocontrol.data.room.Models.EmpresaAsEmpleado;


@Dao
public interface EmpresaAsEmpleadoDao {

    @Insert
    void insertarEmpresaAsEmpleado(EmpresaAsEmpleado empresaAsEmpleado);

    @Query("DELETE FROM EmpresaAsEmpleado")
    void eliminarEmpresaAsEmpleados();

    @Query("SELECT * FROM EmpresaAsEmpleado WHERE idEmpresa = :idEmpresa AND idEmpleado = :idEmpleado")
    EmpresaAsEmpleado getRelacionEmpresaAsEmpleado(String idEmpresa, String idEmpleado);
}
