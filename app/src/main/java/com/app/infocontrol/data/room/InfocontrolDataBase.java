package com.app.infocontrol.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.app.infocontrol.commons.MyApp;
import com.app.infocontrol.data.room.DAO.ControlDeIngresoDao;
import com.app.infocontrol.data.room.DAO.EmpleadoDAO;
import com.app.infocontrol.data.room.DAO.EmpresaAsEmpleadoDao;
import com.app.infocontrol.data.room.DAO.EmpresaAsVehiculoDao;
import com.app.infocontrol.data.room.DAO.EmpresaDAO;
import com.app.infocontrol.data.room.DAO.UsuarioDAO;
import com.app.infocontrol.data.room.DAO.VehiculoDAO;
import com.app.infocontrol.data.room.Migration.Migrations;
import com.app.infocontrol.data.room.Models.ControlDeIngresos;
import com.app.infocontrol.data.room.Models.Empelado;
import com.app.infocontrol.data.room.Models.Empresa;
import com.app.infocontrol.data.room.Models.EmpresaAsEmpleado;
import com.app.infocontrol.data.room.Models.EmpresaAsVehiculo;
import com.app.infocontrol.data.room.Models.Usuario;
import com.app.infocontrol.data.room.Models.Vehiculo;


@Database(entities = {
        Usuario.class,
        Empresa.class,
        Empelado.class,
        Vehiculo.class,
        EmpresaAsEmpleado.class,
        EmpresaAsVehiculo.class,
        ControlDeIngresos.class
}, version = 4, exportSchema = false)
public abstract class InfocontrolDataBase extends RoomDatabase {

    public abstract EmpresaDAO getEmpresaDao();

    public abstract EmpleadoDAO getEmpleadoDao();

    public abstract VehiculoDAO getVehiculoDao();

    public abstract UsuarioDAO getUserDataDao();

    public abstract EmpresaAsEmpleadoDao getEmpresaAsEmpleadoDao();

    public abstract EmpresaAsVehiculoDao getEmpresaAsVehiculoDao();

    public abstract ControlDeIngresoDao getControlDeIngresosDao();

    private static final String NAME_DATABASE = "info_sj_db";

    private static volatile InfocontrolDataBase INSTANCE;

    public static InfocontrolDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InfocontrolDataBase.class) {
                INSTANCE = Room.databaseBuilder(MyApp.getContext(),
                        InfocontrolDataBase.class, NAME_DATABASE)
                        .addMigrations(Migrations.MIGRATION_1_2, Migrations.MIGRATION_2_3, Migrations.MIGRATION_3_4)
                        .build();
            }
        }
        return INSTANCE;
    }
}