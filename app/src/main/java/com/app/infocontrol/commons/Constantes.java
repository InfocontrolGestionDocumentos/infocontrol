package com.app.infocontrol.commons;

public class Constantes {

    /*public static final String URL_BASE = "https://www.infocontrol.com.ar/web/api/mobile/";
    public static final String URL_FOTO = "https://www.infocontrol.com.ar/web/";*/

    /*  Production */
    //public static final String URL_BASE = "https://www.infocontrol.tech/web/api/mobile/";
    //public static final String URL_FOTO = "https://www.infocontrol.tech/web/";

    /*  Test */
    public static final String URL_BASE = "https://www.infocontrol.com.ar/desarrollo/api/mobile/";
    public static final String URL_FOTO = "https://www.infocontrol.com.ar/desarrollo/";

    /*public static final String URL_BASE = "https://www.infocontrol.com.ar/prueba/api/mobile/";
    public static final String URL_FOTO = "https://www.infocontrol.com.ar/prueba/";*/

    // Datos de usuario
    public static final String TOKEN = "Bearer-Token";
    public static final String RECORDAR_USUARIO = "RememberUser";
    public static final String PASS = "PASS";
    public static final String USERNAME ="USERNAME";


    //Tablas en Base de Datos
    public static final String TABLA_EMPRESA = "Empresa";
    public static final String TABLA_EMPLEADO = "Empleado";
    public static final String TABLA_VEHICULO = "Vehiculo";
    public static final String TABLA_EMPRESA_AS_EMPLEADO = "EmpresaAsEmpleado";
    public static final String TABLA_EMPRESA_AS_VEHICULO = "EmpresaAsVehiculo";
    public static final String TABLA_USUARIO = "usuario";
    public static final String TABLA_CONTROL_INGRESOS_BARRICK = "control_ingresos_barrick";

    //Path donde se almacenaran las imagenes descargadas
    public static final String DIR_EMPRESAS = "Empresas";
    public static final String DIR_EMPLEADOS = "Empleados";
    public static final String DIR_VEHICULOS = "Vehiculos";

    //Key para pasar valores a la BuscarActivity
    public static final String KEY_IDEMPRESA = "ID_EMPRESA";

    //Valores del InputEditText
    public static final String CHECK_EMPLEADO = "DNI o RUN";
    public static final String CHECK_VEICULO = "Dominio";

    //QR Escaner
    public static final String QR_RESULT = "dni";
    public static final String QR_RESULT_TIPO = "tipo";

    //Opciones de categoria
    public static final String TIPO_EMPLEADO = "empleado";
    public static final String TIPO_VEHICULO = "vehiculo";
    public static final String TIPO_PROVEEDOR = "proveedor";


    public static final String ID_USUARIO = "ID_USUARIO";

    public static final String NOMBRE_EMPRESA = "NOMBRE_EMPRESA";
}
