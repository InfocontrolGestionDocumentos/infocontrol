package com.app.infocontrol.data.room.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.infocontrol.data.room.Models.Empelado;

import java.util.List;


@Dao
public interface EmpleadoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Empelado empleado);

    @Query("SELECT * FROM Empleado ")
    LiveData<List<Empelado>> getAllEmpleados();

    @Query("SELECT * FROM Empleado")
    List<Empelado> listarEmpleados();

    @Query("SELECT * FROM Empleado WHERE idEmpleado = :id")
    Empelado getEmpleadoByIdEmpelado(String id);

    @Query("SELECT * FROM Empleado WHERE cuil = :cuil")
    Empelado getEmpleadoByCuil(String cuil);

    @Query("SELECT * FROM Empleado WHERE dni = :dni")
    List<Empelado> getEmpeladoByDni(String dni);

    @Query("DELETE FROM Empleado")
    void deletAllEmpleados();

    @Update
    void updateEmpleado(Empelado empleado);

    @Query("SELECT * FROM Empleado WHERE codigoNFC = :idNfc")
    List<Empelado> getEmpeladoByIdNfc(String idNfc);
}
