package com.app.infocontrol.ui.fragment.dialogs;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.infocontrol.R;
import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.commons.ImageSaver;
import com.app.infocontrol.commons.MyApp;
import com.app.infocontrol.data.pojo.response.ResponeLogin.Data;
import com.app.infocontrol.data.pojo.response.ResponeLogin.ResponseLogin;
import com.app.infocontrol.data.pojo.response.ResponseControlDeIngreso.ResponseControlDeIngreso;
import com.app.infocontrol.data.pojo.response.ResponseEmpleados.DataEmpleados;
import com.app.infocontrol.data.pojo.response.ResponseEmpleados.ResponseEmpleados;
import com.app.infocontrol.data.pojo.response.ResponseVehiculos.DataVehiculos;
import com.app.infocontrol.data.pojo.response.ResponseVehiculos.ResponseVehiculo;
import com.app.infocontrol.data.retrofit.clients.EmpleadoClient;
import com.app.infocontrol.data.retrofit.clients.LoginClient;
import com.app.infocontrol.data.retrofit.clients.VehiculoClient;
import com.app.infocontrol.data.retrofit.services.EmpleadoService;
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
import com.app.infocontrol.ui.activity.BuscarActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActualizarDatosDialogFragment extends DialogFragment {

    private ProgressBar pbProgreso;
    private TextView tvDescripcion;
    private MaterialButton btnCancelar;

    //Variables de Retrofir para poder conectarme al API REST
    /*  Clientes    */
    private VehiculoClient vehiculoClient;
    private EmpleadoClient empleadoClient;
    private LoginClient loginCliente;
    /*  Servicios   */
    private VehiculoService vehiculoService;
    private EmpleadoService empleadoService;
    private LoginService loginService;
    /***/

    //Variables de ViewModel para poder manipular los datos en la bdLocal
    /*  Entidades */
    private EmpresaViewModel empresaViewModel;
    private VehiculoViewModel vehiculoViewModel;
    private EmpleadoViewModel empleadoViewModel;
    /*  Entidades de relacion */
    private EmpresaAsVehiculoViewModel empresaAsVehiculoViewModel;
    private EmpresaAsEmpleadoViewModel empresaAsEmpleadoViewModel;
    /*  Control de Ingreso Barrick*/
    private ControlDeIngresosViewModel controlDeIngresosViewModel;
    /***/

    //Variables globales para almacenar temporalmente los valores obtenidos de la API REST
    /*  Para las entidades  */
    private ArrayList<Vehiculo> vehiculoArrayList;
    private ArrayList<Empelado> empleadoArraList;
    /*  Para las entidades de relacion*/
    private ArrayList<EmpresaAsEmpleado> empresaAsEmpleadoArrayList;
    private ArrayList<EmpresaAsVehiculo> empresaAsVehiculoArrayList;

    /***/
    /*  Datos del usuario almacenados*/
    private UsuarioViewModel usuarioViewModel;
    private List<Usuario> usuarioList;

    private List<ControlDeIngresos> controlDeIngresosList;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_actualizar_datos_dialog, container, false);
        /*  Variables de interfaz*/
        pbProgreso = view.findViewById(R.id.progressBar);
        tvDescripcion = view.findViewById(R.id.tvTitulo);
        btnCancelar = view.findViewById(R.id.btnCancelar);
        inicializarVariables();
        tvDescripcion.setText("Conectandose al servidor...");

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        //Actualizar los datos de una sola empresa, la seleccionada al clickear el boton actualizar base de datos
        btnCancelar.setEnabled(true);

        getDatosDeVehiculos(BuscarActivity.idEmpresa);

        btnCancelar.setOnClickListener(v -> dismiss());

        return view;
    }

    private void inicializarVariables() {

        /*  Retrofit*/
        //Clientes
        vehiculoClient = VehiculoClient.getInstance();
        empleadoClient = EmpleadoClient.getInstance();
        loginCliente = LoginClient.getInstance();
        //Servicios
        loginService = loginCliente.getLoginService();
        vehiculoService = vehiculoClient.getVehiculoService();
        empleadoService = empleadoClient.getEmpleadoService();
        /**/

        /*  ViewModel */
        empresaViewModel = ViewModelProviders.of(this).get(EmpresaViewModel.class);
        vehiculoViewModel = ViewModelProviders.of(this).get(VehiculoViewModel.class);
        empleadoViewModel = ViewModelProviders.of(this).get(EmpleadoViewModel.class);
        empresaAsVehiculoViewModel = ViewModelProviders.of(this).get(EmpresaAsVehiculoViewModel.class);
        empresaAsEmpleadoViewModel = ViewModelProviders.of(this).get(EmpresaAsEmpleadoViewModel.class);
        controlDeIngresosViewModel = ViewModelProviders.of(this).get(ControlDeIngresosViewModel.class);
        /**/

        /*  Variables Globales */
        vehiculoArrayList = new ArrayList<>();
        empleadoArraList = new ArrayList<>();
        empresaAsVehiculoArrayList = new ArrayList<>();
        empresaAsEmpleadoArrayList = new ArrayList<>();

        /*Datos del usuario*/
        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel.class);
        usuarioList = usuarioViewModel.getUsuarios();
    }

    private void actualizarToken(String usuario, String pass) {
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

                    getDatosDeVehiculos(BuscarActivity.idEmpresa);
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

    private void getDatosDeVehiculos(String idEmpresa) {
        Empresa empresa = empresaViewModel.getEmpresaByIdAsync(idEmpresa);

        String[] cadena = empresa.getFechaHoraActualizacion().split(" ");
        String[] fechaAux = cadena[0].split("/");
        String nuevaFecha = fechaAux[2] + "-" + fechaAux[1] + "-" + fechaAux[0] + " " + cadena[1];

        Call<ResponseVehiculo> call = vehiculoService.getVehiculos(usuarioList.get(0).getTokenUsuario(), empresa.getIdEmpresa(), nuevaFecha);
        call.enqueue(new Callback<ResponseVehiculo>() {
            @Override
            public void onResponse(@NonNull Call<ResponseVehiculo> call, @NonNull Response<ResponseVehiculo> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<DataVehiculos> dataVehiculos = response.body().getDataVehiculos();
                    for (DataVehiculos vehiculo : dataVehiculos) {
                        vehiculoArrayList.add(new Vehiculo(
                                vehiculo.getIdEntidad(),
                                vehiculo.getCuit(),
                                vehiculo.getValor(),
                                vehiculo.getMensajeGeneral(),
                                vehiculo.getEstado(),
                                vehiculo.getMarca(),
                                vehiculo.getDatos(),
                                Integer.parseInt(vehiculo.getEliminado()),
                                vehiculo.getMensajeGeneralColor(),
                                vehiculo.getMensajeGeneralColorText(),
                                vehiculo.getImagenPath(),
                                vehiculo.getNombreRazonSocial()
                        ));
                        empresaAsVehiculoArrayList.add(new EmpresaAsVehiculo(idEmpresa, vehiculo.getIdEntidad()));
                    }
                    traerDatosDeEmpleados(empresa);

                } else if (response.code() == 401) {
                    actualizarToken(usuarioList.get(0).getUsuarioIdentificacion(), usuarioList.get(0).getUsuarioPassword());
                } else {
                    Toast.makeText(getContext(), "Respuesta Empleados Negativa", Toast.LENGTH_LONG).show();
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

    private void traerDatosDeEmpleados(Empresa empresa) {

        String[] cadena = empresa.getFechaHoraActualizacion().split(" ");
        String[] fechaAux = cadena[0].split("/");
        String nuevaFecha = fechaAux[2] + "-" + fechaAux[1] + "-" + fechaAux[0] + " " + cadena[1];

        Call<ResponseEmpleados> call = empleadoService.getEmpleados(usuarioList.get(0).getTokenUsuario(), empresa.getIdEmpresa(), nuevaFecha);
        call.enqueue(new Callback<ResponseEmpleados>() {
            @Override
            public void onResponse(@NonNull Call<ResponseEmpleados> call, @NonNull Response<ResponseEmpleados> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<DataEmpleados> dataEmpleados = response.body().getDataEmpleados();

                    for (DataEmpleados empleado : dataEmpleados) {
                        empleadoArraList.add(new Empelado(
                                empleado.getIdEntidad(),
                                empleado.getCuit(),
                                empleado.getValor(),
                                empleado.getMensajeGeneral(),
                                empleado.getDatos(),
                                Integer.parseInt(empleado.getAnulado()),
                                Integer.parseInt(empleado.getBajaAfip()),
                                empleado.getEstado(),
                                Integer.parseInt(empleado.getEliminado()),
                                empleado.getMensajeGeneralColor(),
                                empleado.getMensajeGeneralColorText(),
                                empleado.getImagenPath(),
                                empleado.getNombreRazonSocial(),
                                empleado.getCodigoNfc()
                        ));
                        empresaAsEmpleadoArrayList.add(new EmpresaAsEmpleado(empresa.getIdEmpresa(), empleado.getIdEntidad()));
                    }

                    empresa.setFechaHoraActualizacion(formatServerHour(response.body().getDate()));
                    empresaViewModel.updateEmpresa(empresa);
                    btnCancelar.setEnabled(false);
                    new GuardarVehiculosAsincronamente().execute();
                    if (empresa.getIdEmpresa().equals(CustomConstants.ID_ENTERPRISE)) {
                        insertarDatosDeControlDeIngresoEnServidor(empresa.getIdEmpresa());
                    }
                } else {
                    Toast.makeText(getContext(), "Respuesta Empleados Negativa", Toast.LENGTH_LONG).show();
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<ResponseEmpleados> call, @NonNull Throwable t) {
                tvDescripcion.setText("Ocurrio un error, vuelva a intentarlo");
                btnCancelar.setEnabled(true);
                btnCancelar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void insertarDatosDeControlDeIngresoEnServidor(String idEmpresa) {
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
        }
    }

    private String formatServerHour(String date) {
        String[] cadena1 = date.split("T");
        String anioMesDia = cadena1[0];
        String[] subCadena = anioMesDia.split("-");
        String anioArreglado = subCadena[2] + "/" + subCadena[1] + "/" + subCadena[0];
        String[] cadena2 = cadena1[1].split("-");
        String horaMinutosSegundos = cadena2[0];

        return anioArreglado + " " + horaMinutosSegundos;
    }

    @SuppressLint("StaticFieldLeak")
    private class GuardarVehiculosAsincronamente extends AsyncTask<Void, Integer, Boolean> {

        @SuppressLint("CheckResult")
        @Override
        protected Boolean doInBackground(Void... voids) {
            int total = 1;
            for (Vehiculo vehiculo : vehiculoArrayList) {

                vehiculoViewModel.insertarVehiculo(vehiculo);
                guardarImagen(vehiculo.getUrlImagen(), Constantes.DIR_VEHICULOS);
                publishProgress(total);
                total++;
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
            tvDescripcion.setText("Almacenando vehículos");
            pbProgreso.setMax(vehiculoArrayList.size());
            pbProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                new GuardarEmpleadosAsincronamente().execute();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getContext(), "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GuardarEmpleadosAsincronamente extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {

            int total = 1;
            for (Empelado empleado : empleadoArraList) {


                empleadoViewModel.insertarEmpleado(empleado);
                guardarImagen(empleado.getUrlImagen(), Constantes.DIR_EMPLEADOS);
                publishProgress(total);
                total++;
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
            tvDescripcion.setText("Almacenando empleados");
            pbProgreso.setMax(empleadoArraList.size());
            pbProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                new GuardaarEmpresaAsVehiculoAsincronamente().execute();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getContext(), "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GuardaarEmpresaAsVehiculoAsincronamente extends AsyncTask<Void, Integer, Boolean> {

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

        @Override
        protected void onPreExecute() {
            /*tvDescripcion.setText("Comprobando datos 1/2");*/
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
            Toast.makeText(getContext(), "Tarea Cancelada!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GuardarEmpresaAsEmpleadosAsincronamente extends AsyncTask<Void, Integer, Boolean> {
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

        @Override
        protected void onPreExecute() {
            /*tvDescripcion.setText("Comprobando datos 2/2");*/
            pbProgreso.setMax(empresaAsEmpleadoArrayList.size());
            pbProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                BuscarActivity.tvFechaDeActualizacion.setText(empresaViewModel.getEmpresaByIdAsync(BuscarActivity.idEmpresa).getFechaHoraActualizacion());
                dismiss();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getContext(), "La tarea fue cancelada", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarImagen(String urlImagen, String ubicacion) {
        String[] arregloAux = urlImagen.split("/");
        String nombreImagen = arregloAux[arregloAux.length - 1];
        int myWidth = 150;
        int myHeight = 150;
        Glide.with(this)
                .asBitmap()
                .load(Constantes.URL_FOTO + urlImagen)
                .into(new SimpleTarget<Bitmap>(myWidth, myHeight) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        new ImageSaver(getContext()).
                                setFileName(nombreImagen).
                                setDirectoryName(ubicacion).
                                save(resource);
                    }
                });
    }
}
