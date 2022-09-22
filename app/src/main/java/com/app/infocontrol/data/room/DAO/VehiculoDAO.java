package com.app.infocontrol.data.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.infocontrol.data.room.Models.Vehiculo;

import java.util.List;

@Dao
public interface VehiculoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Vehiculo vehiculo);

    @Query("SELECT * FROM Vehiculo")
    LiveData<List<Vehiculo>> getAllVehiculos();

    @Query("SELECT * FROM Vehiculo WHERE idVehiculo = :id")
    Vehiculo getVehiculoById(String id);

    @Query("SELECT * FROM Vehiculo WHERE valor LIKE :valor")
    List<Vehiculo> getVehiculoByValor(String valor);

    @Query("DELETE FROM Vehiculo")
    void deletAllVehiculos();

    @Update
    void updateVehiculo(Vehiculo vehiculo);
}
