package com.app.infocontrol.data.room.Migration;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {

    public static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Empresa ADD COLUMN imagenBase64 TEXT");

        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Empleado ADD COLUMN imagenBase64 TEXT");
            database.execSQL("ALTER TABLE Vehiculo ADD COLUMN imagenBase64 TEXT");
        }
    };

    public static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Usuario ADD COLUMN marcarIngreso TEXT");
        }
    };
}
