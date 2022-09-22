package com.app.infocontrol.worker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.commons.EmpleadosPorEmpresa;
import com.app.infocontrol.commons.ImageSaver;
import com.app.infocontrol.commons.VehiculosPorEmpresa;
import com.app.infocontrol.data.pojo.response.ResponeLogin.Data;
import com.app.infocontrol.data.pojo.response.ResponeLogin.ResponseLogin;
import com.app.infocontrol.data.pojo.response.ResponseControlDeIngreso.ResponseControlDeIngreso;
import com.app.infocontrol.data.pojo.response.ResponseEmpleados.DataEmpleados;
import com.app.infocontrol.data.pojo.response.ResponseEmpleados.ResponseEmpleados;
import com.app.infocontrol.data.pojo.response.ResponseEmpresas.DataEmpresas;
import com.app.infocontrol.data.pojo.response.ResponseEmpresas.ResponseEmpresas;
import com.app.infocontrol.data.pojo.response.ResponseVehiculos.DataVehiculos;
import com.app.infocontrol.data.pojo.response.ResponseVehiculos.ResponseVehiculo;
import com.app.infocontrol.data.retrofit.clients.EmpleadoClient;
import com.app.infocontrol.data.retrofit.clients.EmpresasClient;
import com.app.infocontrol.data.retrofit.clients.LoginClient;
import com.app.infocontrol.data.retrofit.clients.VehiculoClient;
import com.app.infocontrol.data.retrofit.services.EmpleadoService;
import com.app.infocontrol.data.retrofit.services.EmpresasService;
import com.app.infocontrol.data.retrofit.services.LoginService;
import com.app.infocontrol.data.retrofit.services.VehiculoService;
import com.app.infocontrol.data.room.Models.ControlDeIngresos;
import com.app.infocontrol.data.room.Models.Empelado;
import com.app.infocontrol.data.room.Models.Empresa;
import com.app.infocontrol.data.room.Models.EmpresaAsEmpleado;
import com.app.infocontrol.data.room.Models.EmpresaAsVehiculo;
import com.app.infocontrol.data.room.Models.Usuario;
import com.app.infocontrol.data.room.Models.Vehiculo;
import com.app.infocontrol.data.viewmodel.ControlDeIngresosViewModel;
import com.app.infocontrol.data.viewmodel.EmpleadoViewModel;
import com.app.infocontrol.data.viewmodel.EmpresaAsEmpleadoViewModel;
import com.app.infocontrol.data.viewmodel.EmpresaAsVehiculoViewModel;
import com.app.infocontrol.data.viewmodel.EmpresaViewModel;
import com.app.infocontrol.data.viewmodel.UsuarioViewModel;
import com.app.infocontrol.data.viewmodel.VehiculoViewModel;
import com.app.infocontrol.ui.CustomConstants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesManager {
    /*
     *  1- Definir las variables para conectarme al API REST
     *  2- Definir las variables para manipular la base de datos
     *  3- Definir variables de arreglo (ArrayList o List) globales para recolectar la información desde el servidor
     *  4- Definir las clases asincronas que manipularan el almacenamiento o llamar al ViewModel enviando el arreglo de datos para que este realice el almacenammiento
     *  5- Analizar como manipular la descarga y almacenamiento local de las imagenes de los empleados y vehiculos.
     *
     */

    //Variables de Retrofir para poder conectarme al API REST
    /*  Clientes    */
    private static EmpresasClient empresasClient;
    private static VehiculoClient vehiculoClient;
    private static EmpleadoClient empleadoClient;
    private static LoginClient loginCliente;
    /*  Servicios   */
    private static EmpresasService empresasService;
    private static VehiculoService vehiculoService;
    private static EmpleadoService empleadoService;
    private static LoginService loginService;
    /***/

    //Variables de ViewModel para poder manipular los datos en la bdLocal
    /*  Entidades */
    private static EmpresaViewModel empresaViewModel;
    private static VehiculoViewModel vehiculoViewModel;
    private static EmpleadoViewModel empleadoViewModel;
    /*  Entidades de relacion */
    private static EmpresaAsVehiculoViewModel empresaAsVehiculoViewModel;
    private static EmpresaAsEmpleadoViewModel empresaAsEmpleadoViewModel;
    /*  Control de Ingreso Barrick*/
    private static ControlDeIngresosViewModel controlDeIngresosViewModel;
    /***/

    //Variables globales para almacenar temporalmente los valores obtenidos de la API REST
    /*  Para las entidades  */
    private static ArrayList<DataEmpresas> empresaArrayList;
    private static ArrayList<VehiculosPorEmpresa> vehiculosPorEmpresaArrayList;
    private static ArrayList<EmpleadosPorEmpresa> empleadosPorEmpresaArrayList;
    /*  Para las entidades de relacion*/
    private static ArrayList<EmpresaAsEmpleado> empresaAsEmpleadoArrayList;
    private static ArrayList<EmpresaAsVehiculo> empresaAsVehiculoArrayList;
    /***/

    /*  Variables auxiliares*/
    private static int totalDeEmpeladosPorEmpresa = 0, totalDeVehiculosPorEmpresa = 0;

    /*Datos del usuario por si es necesario solicitar de nuevo el Token*/
    private static UsuarioViewModel usuarioViewModel;
    private static List<Usuario> usuarioList;

    private static List<ControlDeIngresos> controlDeIngresosList;

    @SuppressLint("StaticFieldLeak")
    private static TextView tvDescripcion;
    @SuppressLint("StaticFieldLeak")
    private static ProgressBar pbProgreso;
    private static MaterialButton btnCancelar;

    private static DialogFragment dialog;
    @SuppressLint("StaticFieldLeak")
    private static Context contextActivity;

    /**
     * @param descripcion Descripción que se muestra en el DialogFragment
     * @param progreso    Progreso que demanda la tarea
     * @param cancelar    Permite cancelar la tarea si esta demandando mucho tiempo
     */
    public static void loadingTask(TextView descripcion, ProgressBar progreso, MaterialButton cancelar, DialogFragment dialogFragment) {

        dialog = dialogFragment;
        contextActivity = dialog.getContext();
        tvDescripcion = descripcion;
        pbProgreso = progreso;
        btnCancelar = cancelar;
        inicializarVariables();
        obtenerDatosDeLasEmpresas();
    }

    /**
     * @param descripcion Descripción que se muestra en el DialogFragment
     * @param progreso    Progreso que demanda la tarea
     * @param cancelar    Permite cancelar la tarea si esta demandando mucho tiempo
     */
    public static void uploadTask(TextView descripcion, ProgressBar progreso, MaterialButton cancelar, DialogFragment dialogFragment, String idEmpresa) {

        dialog = dialogFragment;
        contextActivity = dialog.getContext();
        tvDescripcion = descripcion;
        pbProgreso = progreso;
        btnCancelar = cancelar;
        inicializarVariables();

        Empresa empresa = empresaViewModel.getEmpresaByIdAsync(idEmpresa);

        String[] cadena = empresa.getFechaHoraActualizacion().split(" ");
        String[] fechaAux = cadena[0].split("/");
        String nuevaFecha = fechaAux[2] + "-" + fechaAux[1] + "-" + fechaAux[0] + " " + cadena[1];

        obtenerVehiculosPorEmpresa(empresa.getIdEmpresa(), nuevaFecha);
    }

    public static void syncServerTask(Context context) {
        inicializarVariables();
        contextActivity = context;
    }

    private static void inicializarVariables() {

        /*  Retrofit*/
        //Clientes
        empresasClient = EmpresasClient.getInstance();
        loginCliente = LoginClient.getInstance();
        vehiculoClient = VehiculoClient.getInstance();
        empleadoClient = EmpleadoClient.getInstance();
        //Servicios
        empresasService = empresasClient.getEmpresasService();
        loginService = loginCliente.getLoginService();
        vehiculoService = vehiculoClient.getVehiculoService();
        empleadoService = empleadoClient.getEmpleadoService();
        /**/

        /*  ViewModel */
        empresaViewModel = ViewModelProviders.of((FragmentActivity) contextActivity).get(EmpresaViewModel.class);
        vehiculoViewModel = ViewModelProviders.of((FragmentActivity) contextActivity).get(VehiculoViewModel.class);
        empleadoViewModel = ViewModelProviders.of((FragmentActivity) contextActivity).get(EmpleadoViewModel.class);
        empresaAsVehiculoViewModel = ViewModelProviders.of((FragmentActivity) contextActivity).get(EmpresaAsVehiculoViewModel.class);
        empresaAsEmpleadoViewModel = ViewModelProviders.of((FragmentActivity) contextActivity).get(EmpresaAsEmpleadoViewModel.class);
        controlDeIngresosViewModel = ViewModelProviders.of((FragmentActivity) contextActivity).get(ControlDeIngresosViewModel.class);
        /**/

        /*  Variables Globales */
        empresaArrayList = new ArrayList<>();
        vehiculosPorEmpresaArrayList = new ArrayList<>();
        empleadosPorEmpresaArrayList = new ArrayList<>();
        empresaAsVehiculoArrayList = new ArrayList<>();
        empresaAsEmpleadoArrayList = new ArrayList<>();

        /*Informacion del usuario*/
        usuarioViewModel = ViewModelProviders.of((FragmentActivity) contextActivity).get(UsuarioViewModel.class);
        usuarioList = usuarioViewModel.getUsuarios();
    }

    private static void obtenerDatosDeLasEmpresas() {
        Call<ResponseEmpresas> call = empresasService.getEmpresas(usuarioList.get(0).getTokenUsuario());
        call.enqueue(new Callback<ResponseEmpresas>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ResponseEmpresas> call, @NonNull Response<ResponseEmpresas> response) {
                if (response.isSuccessful()) {

                    tvDescripcion.setText("Obteniendo datos...");
                    assert response.body() != null;
                    empresaArrayList = response.body().getDataEmpresas();
                    for (DataEmpresas empresa : empresaArrayList) {
                        if (empresa.getFechaHoraActualizacion() == null || empresa.getFechaHoraActualizacion().isEmpty()) {
                            if (empresaViewModel.existeEmpresaAsync(empresa.getIdEmpresaAsociada())) {
                                empresa.setFechaHoraActualizacion(empresaViewModel.getEmpresaByIdAsync(empresa.getIdEmpresaAsociada()).getFechaHoraActualizacion());
                            } else {
                                empresa.setFechaHoraActualizacion("1999-01-01 00:00:00");
                            }
                        }
                        obtenerVehiculosPorEmpresa(empresa.getIdEmpresaAsociada(), empresa.getFechaHoraActualizacion());
                    }
                } else if (response.code() == 401) {
                    actualizarToken(usuarioList.get(0).getUsuarioIdentificacion(), usuarioList.get(0).getUsuarioPassword());
                } else {
                    Toast.makeText(contextActivity, "Ocurrio un error, vuelva a intentarlo", Toast.LENGTH_LONG).show();
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<ResponseEmpresas> call, @NonNull Throwable t) {
                /*errorDeConexionDatosInestables();*/
                tvDescripcion.setText("Ocurrio un error, vuelva a intentarlo");
                btnCancelar.setVisibility(View.VISIBLE);
                btnCancelar.setEnabled(true);
            }
        });
    }

    public static void actualizarToken(String usuario, String pass) {
        Call<ResponseLogin> call = loginService.postLogin(usuario, pass);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NonNull Call<ResponseLogin> call, @NonNull Response<ResponseLogin> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Data data = response.body().getData();

                    Usuario user = usuarioList.get(0);
                    user.setTokenUsuario(data.getBearer());
                    usuarioViewModel.updateUser(user);
                    usuarioList.clear();
                    usuarioList = usuarioViewModel.getUsuarios();

                    obtenerDatosDeLasEmpresas();
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<ResponseLogin> call, @NonNull Throwable t) {
                /*errorDeConexionDatosInestables();*/
                tvDescripcion.setText("Ocurrio un error, vuelva a intentarlo");
                btnCancelar.setVisibility(View.VISIBLE);
                btnCancelar.setEnabled(true);
            }
        });
    }

    private static void obtenerVehiculosPorEmpresa(String idEmpresa, String fecha) {

        String nuevaFecha;
        try {
            if (!fecha.equals("1999-01-01 00:00:00")) {
                String[] cadena = fecha.split(" ");
                String[] fechaAux = cadena[0].split("/");
                nuevaFecha = fechaAux[2] + "-" + fechaAux[1] + "-" + fechaAux[0] + " " + cadena[1];
            } else nuevaFecha = fecha;
        } catch (Exception e) {
            nuevaFecha = fecha;
        }

        Call<ResponseVehiculo> call = vehiculoService.getVehiculos(usuarioList.get(0).getTokenUsuario(), idEmpresa, nuevaFecha);
        call.enqueue(new Callback<ResponseVehiculo>() {
            @Override
            public void onResponse(@NonNull Call<ResponseVehiculo> call, @NonNull Response<ResponseVehiculo> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<DataVehiculos> dataVehiculos = response.body().getDataVehiculos();
                    vehiculosPorEmpresaArrayList.add(
                            new VehiculosPorEmpresa(
                                    idEmpresa,
                                    dataVehiculos
                            )
                    );
                    if (empresaArrayList.size() == vehiculosPorEmpresaArrayList.size()) {
                        for (int i = 0; i < vehiculosPorEmpresaArrayList.size(); i++) {
                            totalDeVehiculosPorEmpresa += vehiculosPorEmpresaArrayList.get(i).getVehiculos().size();
                            for (int j = 0; j < vehiculosPorEmpresaArrayList.get(i).getVehiculos().size(); j++) {
                                empresaAsVehiculoArrayList.add(
                                        new EmpresaAsVehiculo(
                                                vehiculosPorEmpresaArrayList.get(i).getIdEmpresa(),
                                                vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getIdEntidad()
                                        )
                                );
                            }
                        }
                        for (DataEmpresas empresa : empresaArrayList) {
                            traerDatosDeEmpleadosPorEmpresa(empresa.getIdEmpresaAsociada(), empresa.getFechaHoraActualizacion());
                        }
                    }

                } else {
                    Toast.makeText(contextActivity, "Respuesta Empleados Negativa", Toast.LENGTH_LONG).show();
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<ResponseVehiculo> call, @NonNull Throwable t) {
                tvDescripcion.setText("Ocurrio un error, vuelva a intentarlo");
                btnCancelar.setVisibility(View.VISIBLE);
                btnCancelar.setEnabled(true);
            }
        });

    }

    private static void traerDatosDeEmpleadosPorEmpresa(String idEmpresa, String fecha) {

        String nuevaFecha;
        try {

            if (!fecha.equals("1999-01-01 00:00:00")) {
                String[] cadena = fecha.split(" ");
                String[] fechaAux = cadena[0].split("/");
                nuevaFecha = fechaAux[2] + "-" + fechaAux[1] + "-" + fechaAux[0] + " " + cadena[1];
            } else nuevaFecha = fecha;
        } catch (Exception e) {
            nuevaFecha = fecha;
        }


        Call<ResponseEmpleados> call = empleadoService.getEmpleados(usuarioList.get(0).getTokenUsuario(), idEmpresa, nuevaFecha);
        call.enqueue(new Callback<ResponseEmpleados>() {
            @Override
            public void onResponse(@NonNull Call<ResponseEmpleados> call, @NonNull Response<ResponseEmpleados> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<DataEmpleados> dataEmpleados = response.body().getDataEmpleados();
                    empleadosPorEmpresaArrayList.add(
                            new EmpleadosPorEmpresa(
                                    idEmpresa,
                                    dataEmpleados
                            )
                    );

                    if (empresaArrayList.size() == empleadosPorEmpresaArrayList.size()) {
                        for (int i = 0; i < empleadosPorEmpresaArrayList.size(); i++) {
                            totalDeEmpeladosPorEmpresa += empleadosPorEmpresaArrayList.get(i).getEmpleados().size();
                            empresaArrayList.get(i).setFechaHoraActualizacion(formatServerHour(response.body().getDate()));
                            for (int j = 0; j < empleadosPorEmpresaArrayList.get(i).getEmpleados().size(); j++) {
                                empresaAsEmpleadoArrayList.add(
                                        new EmpresaAsEmpleado(
                                                empleadosPorEmpresaArrayList.get(i).getIdEmpresa(),
                                                empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getIdEntidad()
                                        )
                                );
                            }
                        }
                        //Actualizar fecha de sincronizacion respecto a la obtenida en la restpuesta de DataEmpleados
                        btnCancelar.setEnabled(false);
                        new GuardarEmpresasAsincronamente().execute();
                        if (empresaArrayList.size() == 1 && empresaArrayList.get(0).getIdEmpresaAsociada().equals(CustomConstants.ID_ENTERPRISE)) {
                            insertarDatosDeControlDeIngresoEnServidor(empresaArrayList.get(0).getIdEmpresaAsociada());
                        }
                    }

                } else {
                    Toast.makeText(contextActivity, "Respuesta Empleados Negativa", Toast.LENGTH_LONG).show();
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<ResponseEmpleados> call, @NonNull Throwable t) {
                tvDescripcion.setText("Ocurrio un error, vuelva a intentarlo");
                btnCancelar.setVisibility(View.VISIBLE);
                btnCancelar.setEnabled(true);
            }
        });
    }

    private static void insertarDatosDeControlDeIngresoEnServidor(String idEmpresa) {
        /*Call<ResponseControlDeIngreso> call = empleadoService.eliminarRegistrosEnServidor(usuarioList.get(0).getTokenUsuario());
        call.enqueue(new Callback<ResponseControlDeIngreso>() {
            @Override
            public void onResponse(Call<ResponseControlDeIngreso> call, Response<ResponseControlDeIngreso> response) {
                if(response.isSuccessful()){*/
        //Obtener los datos almacenados y mandarlos en el request de insertar
        controlDeIngresosList = controlDeIngresosViewModel.getAllRegistros();
        Call<ResponseControlDeIngreso> call2;
        for (ControlDeIngresos registro : controlDeIngresosList) {
            call2 = empleadoService.postInsertarControlDeIngreso(usuarioList.get(0).getTokenUsuario(), registro);
            call2.enqueue(new Callback<ResponseControlDeIngreso>() {
                @Override
                public void onResponse(@NonNull Call<ResponseControlDeIngreso> call, @NonNull Response<ResponseControlDeIngreso> response) {
                    if (response.isSuccessful()) {
                        Log.i("REGISTRO_INSERTADO", "Se inserto el registro");
                    } else {
                        Log.i("REGISTRO_ERROR", "Ocurrió un error al insertar el registro");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseControlDeIngreso> call, @NonNull Throwable t) {

                }
            });
        }/*
                }else {
                    Toast.makeText(contextActivity, "Resouesta de sincronizacion negativa", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseControlDeIngreso> call, Throwable t) {

            }
        });*/
    }

    private static String formatServerHour(String date) {
        String[] cadena1 = date.split("T");
        String anioMesDia = cadena1[0];
        String[] subCadena = anioMesDia.split("-");
        String anioArreglado = subCadena[2] + "/" + subCadena[1] + "/" + subCadena[0];
        String[] cadena2 = cadena1[1].split("-");
        String horaMinutosSegundos = cadena2[0];

        return anioArreglado + " " + horaMinutosSegundos;
    }

    private static class GuardarEmpresasAsincronamente extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            Empresa empresa;
            for (int i = 0; i < empresaArrayList.size(); i++) {
                empresa = new Empresa(
                        empresaArrayList.get(i).getIdEmpresaAsociada(),
                        empresaArrayList.get(i).getNombre(),
                        Integer.parseInt(empresaArrayList.get(i).getActiva()),
                        empresaArrayList.get(i).getTipoCliente(),
                        Integer.parseInt(empresaArrayList.get(i).getCantidadPendientes()),
                        Integer.parseInt(empresaArrayList.get(i).getBajaAfipDiasGracia()),
                        empresaArrayList.get(i).getLogo(),
                        empresaArrayList.get(i).getFechaHoraActualizacion()
                );
                empresaViewModel.insertarEmpresa(empresa);
                guardarImagen(empresa.getLogo(), Constantes.DIR_EMPRESAS);
                publishProgress(i + 1);
                if (isCancelled())
                    break;
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0];

            pbProgreso.setProgress(progreso);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {
            tvDescripcion.setText("Almacenando empresas");
            pbProgreso.setIndeterminate(false);
            pbProgreso.setMax(empresaArrayList.size());
            pbProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                new GuardarVehiculosAsincronamente().execute();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(contextActivity, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }
    }

    private static class GuardarVehiculosAsincronamente extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            Vehiculo vehiculo;
            int total = 1;
            for (int i = 0; i < vehiculosPorEmpresaArrayList.size(); i++) {
                for (int j = 0; j < vehiculosPorEmpresaArrayList.get(i).getVehiculos().size(); j++) {
                    vehiculo = new Vehiculo(
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getIdEntidad(),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getCuit(),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getValor(),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getMensajeGeneral(),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getEstado(),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getMarca(),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getDatos(),
                            Integer.parseInt(vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getEliminado()),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getMensajeGeneralColor(),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getMensajeGeneralColorText(),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getImagenPath(),
                            vehiculosPorEmpresaArrayList.get(i).getVehiculos().get(j).getNombreRazonSocial()
                    );
                    if (!vehiculoViewModel.existeVehiculo(vehiculo.getIdVehiculo())) {
                        vehiculoViewModel.insertarVehiculo(vehiculo);
                        guardarImagen(vehiculo.getUrlImagen(), Constantes.DIR_VEHICULOS);
                    } else {
                        vehiculoViewModel.updateVehiculos(vehiculo);
                    }
                    publishProgress(total);
                    total++;
                    if (isCancelled())
                        break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0];

            pbProgreso.setProgress(progreso);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {
            tvDescripcion.setText("Almacenando vehiculos");
            pbProgreso.setMax(totalDeVehiculosPorEmpresa);
            pbProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                new GuardarEmpleadosAsincronamente().execute();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(contextActivity, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }
    }

    private static class GuardarEmpleadosAsincronamente extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            Empelado empleado;
            int total = 1;
            for (int i = 0; i < empleadosPorEmpresaArrayList.size(); i++) {
                for (int j = 0; j < empleadosPorEmpresaArrayList.get(i).getEmpleados().size(); j++) {
                    empleado = new Empelado(
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getIdEntidad(),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getCuit(),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getValor(),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getMensajeGeneral(),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getDatos(),
                            Integer.parseInt(empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getAnulado()),
                            Integer.parseInt(empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getBajaAfip()),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getEstado(),
                            Integer.parseInt(empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getEliminado()),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getMensajeGeneralColor(),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getMensajeGeneralColorText(),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getImagenPath(),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getNombreRazonSocial(),
                            empleadosPorEmpresaArrayList.get(i).getEmpleados().get(j).getCodigoNfc()
                    );
                    if (!empleadoViewModel.existeEmpleado(empleado.getIdEmpleado())) {
                        empleadoViewModel.insertarEmpleado(empleado);
                        guardarImagen(empleado.getUrlImagen(), Constantes.DIR_EMPLEADOS);
                    } else {
                        empleadoViewModel.updateEmpleado(empleado);
                    }
                    publishProgress(total);
                    total++;
                    if (isCancelled())
                        break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0];

            pbProgreso.setProgress(progreso);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {
            tvDescripcion.setText("Almacenando empleados");
            pbProgreso.setMax(totalDeEmpeladosPorEmpresa);
            pbProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                new GuardaarEmpresaAsVehiculoAsincronamente().execute();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(contextActivity, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }
    }

    private static class GuardaarEmpresaAsVehiculoAsincronamente extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i = 0; i < empresaAsVehiculoArrayList.size(); i++) {
                if (!empresaAsVehiculoViewModel.existeRelacion(empresaAsVehiculoArrayList.get(i).getIdEmpresa(), empresaAsVehiculoArrayList.get(i).getIdVehiculo())) {
                    empresaAsVehiculoViewModel.insertarEmpresaAsVehiculo(empresaAsVehiculoArrayList.get(i));
                }
                publishProgress(i + 1);
                if (isCancelled()) {
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0];

            pbProgreso.setProgress(progreso);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {
            tvDescripcion.setText("Comprobando datos 1/2");
            pbProgreso.setMax(empresaAsVehiculoArrayList.size());
            pbProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                new GuardarEmpresaAsEmpleadosAsincronamente().execute();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(contextActivity, "Tarea Cancelada!", Toast.LENGTH_SHORT).show();
        }
    }

    private static class GuardarEmpresaAsEmpleadosAsincronamente extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i = 0; i < empresaAsEmpleadoArrayList.size(); i++) {
                if (!empresaAsEmpleadoViewModel.existeEmpreasAsEmpelado(empresaAsEmpleadoArrayList.get(i).getIdEmpresa(), empresaAsEmpleadoArrayList.get(i).getIdEmpleado())) {
                    empresaAsEmpleadoViewModel.insertarEmpresaAsEmpleado(empresaAsEmpleadoArrayList.get(i));
                }
                publishProgress(i + 1);

                if (isCancelled()) {
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0];
            pbProgreso.setProgress(progreso);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {
            tvDescripcion.setText("Comprobando datos 2/2");
            pbProgreso.setMax(empresaAsEmpleadoArrayList.size());
            pbProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {

                dialog.dismiss();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(contextActivity, "La tarea fue cancelada", Toast.LENGTH_SHORT).show();
        }
    }

    private static void guardarImagen(String urlImagen, String ubicacion) {
        String[] arregloAux = urlImagen.split("/");
        String nombreImagen = arregloAux[arregloAux.length - 1];
        int myWidth = 150;
        int myHeight = 150;
        Glide.with(contextActivity)
                .asBitmap()
                .load(Constantes.URL_FOTO + urlImagen)
                .into(new SimpleTarget<Bitmap>(myWidth, myHeight) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        new ImageSaver(contextActivity).
                                setFileName(nombreImagen).
                                setDirectoryName(ubicacion).
                                save(resource);
                    }
                });
    }

}

