package com.app.infocontrol.data.room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.infocontrol.data.room.Models.ControlDeIngresos;

import java.util.List;


@Dao
public interface ControlDeIngresoDao {

    @Insert
    void insert(ControlDeIngresos registro);

    @Update
    void update(ControlDeIngresos registro);

    @Query("SELECT * FROM control_ingresos_barrick")
    List<ControlDeIngresos> getAll();

    @Query("SELECT * FROM control_ingresos_barrick WHERE idRegistro = :idRegistro AND idEmpresa = :idEmpresa")
    ControlDeIngresos getRegistroByidRegistroAndidEmpresa(String idRegistro, String idEmpresa);


    @Query("DELETE FROM control_ingresos_barrick WHERE idControl = :idControl")
    void deleteByIdControl(int idControl);


    @Query("SELECT * FROM control_ingresos_barrick WHERE idRegistro = :idEmpleado AND idEmpresa = :idEmpresa AND idUsuarioEgreso = :salida")
    ControlDeIngresos getRegistroConEntradaByIds(String idEmpleado, String idEmpresa, String salida);
}
