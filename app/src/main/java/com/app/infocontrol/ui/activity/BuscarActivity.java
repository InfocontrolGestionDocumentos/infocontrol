package com.app.infocontrol.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.infocontrol.R;
import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.commons.Empleado.Dato;
import com.app.infocontrol.commons.VerificarEstadoDeConexion;
import com.app.infocontrol.data.pojo.request.RequestAccionARegistrarEmpleado;
import com.app.infocontrol.data.pojo.request.RequestRegistrarEmpleado;
import com.app.infocontrol.data.pojo.response.ResponeLogin.Data;
import com.app.infocontrol.data.pojo.response.ResponeLogin.ResponseLogin;
import com.app.infocontrol.data.pojo.response.ResponseEmpleados.DataRegistro;
import com.app.infocontrol.data.pojo.response.ResponseEmpleados.ResponseRegistroEmpleado;
import com.app.infocontrol.data.retrofit.clients.EmpleadoClient;
import com.app.infocontrol.data.retrofit.clients.LoginClient;
import com.app.infocontrol.data.retrofit.services.EmpleadoService;
import com.app.infocontrol.data.retrofit.services.LoginService;
import com.app.infocontrol.data.room.Models.ControlDeIngresos;
import com.app.infocontrol.data.room.Models.Empelado;
import com.app.infocontrol.data.room.Models.Empresa;
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
import com.app.infocontrol.ui.fragment.dialogs.ActualizarDatosDialogFragment;
import com.app.infocontrol.worker.ServicesManager;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarActivity extends AppCompatActivity {

    private MaterialButton btnVolver, btnBuscar, btnEscanear, btnActualizarBD;
    private TextInputEditText tietBuscar;
    private TextInputLayout tilBuscar;
    private RadioGroup radioGroup;
    private TextView tvNombreDeLaEmpresa, tvOpcional;
    public static TextView tvFechaDeActualizacion;
    private ListView listaOpciones;
    private LinearLayout progressLayout;

    public static RadioButton rbEmpleado, rbVeiculo;

    private static final int PERMISSION_REQUEST_CODE = 1;

    private EmpleadoViewModel empleadoViewModel;
    private VehiculoViewModel vehiculoViewModel;
    private EmpresaAsEmpleadoViewModel empresaAsEmpleadoViewModel;
    private EmpresaAsVehiculoViewModel empresaAsVehiculoViewModel;
    private ControlDeIngresosViewModel controlDeIngresosViewModel;
    private UsuarioViewModel usuarioViewModel;

    /*  Retrofit */
    private EmpleadoClient empleadoClient;
    private EmpleadoService empleadoService;
    /*  Datos del usuario almacenados*/
    private List<Usuario> usuarioList;


    public static Dato[] dato;
    public static String estadoEmpelado, urlFoto, estadoContratista, colorTexto;

    public static String idEmpresa;

    private EmpresaViewModel empresaViewModel;

    /*  Variables para la lectura de la tarjeta NFC */
    private IntentFilter[] filters;
    private String[][] techs;
    private PendingIntent pendingIntent;
    private NfcAdapter adapter;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());

    private String ingresoEgreso = "";

    private Usuario usuario;

    private static LoginClient loginCliente;
    private static LoginService loginService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        idEmpresa = getIntent().getStringExtra(Constantes.KEY_IDEMPRESA);

        inicializarViewModel();
        inicializarServicios();
        linkVariables();

        Empresa empresa = empresaViewModel.getEmpresaByIdAsync(idEmpresa);
        tvFechaDeActualizacion.setText(empresa.getFechaHoraActualizacion());
        tvNombreDeLaEmpresa.setText(empresa.getNombre());

        btnVolver.setOnClickListener(v -> onBackPressed());

        verificarSiPuedoMarcarIngreso();

        tietBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvOpcional.setVisibility(View.GONE);
                listaOpciones.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnBuscar.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            usuario = null;
            usuario = usuarioViewModel.getUsuarios().get(0);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(tvFechaDeActualizacion.getWindowToken(), 0);
            if (Objects.requireNonNull(tietBuscar.getText()).toString().isEmpty()) {
                tietBuscar.setError("Debe completar este campo");
                tietBuscar.requestFocus();
            } else {
                if (rbEmpleado.isChecked()) {
                    buscarEmpleado(tietBuscar.getText().toString().trim());
                } else if (rbVeiculo.isChecked()) {
                    buscarVehiculo(tietBuscar.getText().toString().trim());
                }
            }
        });

        btnEscanear.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            usuario = null;
            usuario = usuarioViewModel.getUsuarios().get(0);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(tvFechaDeActualizacion.getWindowToken(), 0);
            if (checkPermission()) {
                startActivityForResult(new Intent(BuscarActivity.this, ScanQRCode.class), 1);
            } else {
                requestPermission();
            }
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbEmpleados:
                    tilBuscar.setHint(Constantes.CHECK_EMPLEADO);
                    tietBuscar.setText("");
                    break;
                case R.id.rbVeiculos:
                    tilBuscar.setHint(Constantes.CHECK_VEICULO);
                    tietBuscar.setText("");
                    break;
            }
        });

        btnActualizarBD.setOnClickListener(v -> {
            if (VerificarEstadoDeConexion.isNetDisponible(this)) {
                ActualizarDatosDialogFragment dialogFragment = new ActualizarDatosDialogFragment();
                dialogFragment.setCancelable(false);
                dialogFragment.show(getSupportFragmentManager(), "ActualizarDatosDialogFragment");

            } else {
                Toast.makeText(this, "Verifique su conexión a internet", Toast.LENGTH_SHORT).show();
            }

        });

        /*  Inicializacion de variables para NFC */
        pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter mifare = new IntentFilter((NfcAdapter.ACTION_TECH_DISCOVERED));
        filters = new IntentFilter[]{mifare};
        techs = new String[][]{new String[]{NfcA.class.getName()}};
        adapter = NfcAdapter.getDefaultAdapter(this);

    }

    private void verificarSiPuedoMarcarIngreso(){
        usuario = null;
        usuario = usuarioViewModel.getUsuarios().get(0);
        Gson gson = new Gson();
        Type marcarIngresoType = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> marcarIngreso = gson.fromJson(usuario.getMarcarIngreso(), marcarIngresoType);
        if(marcarIngreso != null) {
            for (String idMarcaIngreso : marcarIngreso) {
                if (idEmpresa.equals(idMarcaIngreso)) {
                    rbVeiculo.setVisibility(View.GONE);
                    break;
                }
            }
        }
    }

    private void inicializarServicios() {

        empleadoClient = EmpleadoClient.getInstance();
        empleadoService = empleadoClient.getEmpleadoService();
        loginCliente = LoginClient.getInstance();
        loginService = loginCliente.getLoginService();
        usuarioList = usuarioViewModel.getUsuarios();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adapter != null) {
            adapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.enableForegroundDispatch(this, pendingIntent, filters, techs);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        buscarEmpleado(ByteArrayToHexString(Objects.requireNonNull(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID))));

    }

    private String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

    private void buscarVehiculo(String valor) {
        List<Vehiculo> vehiculos = vehiculoViewModel.getDataVehiculoByValor(valor);
        if (vehiculos != null) {
            Gson gson = new Gson();
            Type marcarIngresoType = new TypeToken<ArrayList<String>>() {
            }.getType();
            ArrayList<String> marcarIngreso = gson.fromJson(usuario.getMarcarIngreso(), marcarIngresoType);
            if (vehiculos.size() > 0) {
                ArrayList<Vehiculo> vehiculosSeleccionados = new ArrayList<>();
                for (Vehiculo vehi : vehiculos) {
                    if (empresaAsVehiculoViewModel.existeRelacionAsync(idEmpresa, vehi.getIdVehiculo())) {
                        vehiculosSeleccionados.add(vehi);
                    }
                }

                if (vehiculosSeleccionados.size() == 0) {
                    tietBuscar.setError("No corresponde a un vehículo registrado, vuelva a intentarlo");
                    tietBuscar.requestFocus();
                    return;
                }

                if (vehiculosSeleccionados.size() > 1) {
                    tvOpcional.animate()
                            .alpha(1.0f)
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    tvOpcional.setVisibility(View.VISIBLE);
                                }
                            });
                    listaOpciones.animate()
                            .alpha(1.0f)
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    listaOpciones.setVisibility(View.VISIBLE);
                                }
                            });

                    ArrayAdapter<String> adapter;
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
                    for (Vehiculo vehi : vehiculosSeleccionados) {
                        adapter.add(vehi.getRazonSocial());

                    }
                    listaOpciones.setAdapter(adapter);
                    listaOpciones.setOnItemClickListener((parent, view, position, id) -> {

                        estadoContratista = vehiculosSeleccionados.get(position).getMensajeGeneral();
                        urlFoto = vehiculosSeleccionados.get(position).getUrlImagen();
                        estadoEmpelado = vehiculosSeleccionados.get(position).getEstado();
                        colorTexto = vehiculosSeleccionados.get(position).getColorTextoMensajeGeneral();
                        dato = gson.fromJson(vehiculosSeleccionados.get(position).getDatos(), Dato[].class);
                        boolean puedeMarcarIngreso = false;
                        if(marcarIngreso != null) {
                            for (String idMarcaIngreso : marcarIngreso) {
                                if (idEmpresa.equals(idMarcaIngreso)) {
                                    puedeMarcarIngreso = true;
                                    break;
                                }
                            }
                        }
                        if (puedeMarcarIngreso) {
                            consultarRegistro(vehiculosSeleccionados.get(position).getIdVehiculo(), "vehiculo");
//                            showDiag();
                        } else {
                            Intent i = new Intent(BuscarActivity.this, RenderActivity.class);
                            Activity activity = BuscarActivity.this;
                            activity.startActivity(i);
                            activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
                        }
                    });
                } else {
                    estadoContratista = vehiculosSeleccionados.get(0).getMensajeGeneral();
                    urlFoto = vehiculosSeleccionados.get(0).getUrlImagen();
                    estadoEmpelado = vehiculosSeleccionados.get(0).getEstado();
                    colorTexto = vehiculosSeleccionados.get(0).getColorTextoMensajeGeneral();

                    dato = gson.fromJson(vehiculosSeleccionados.get(0).getDatos(), Dato[].class);
                    boolean puedeMarcarIngreso = false;
                    if(marcarIngreso != null) {
                        for (String idMarcaIngreso : marcarIngreso) {
                            if (idEmpresa.equals(idMarcaIngreso)) {
                                puedeMarcarIngreso = true;
                                break;
                            }
                        }
                    }
                    if (puedeMarcarIngreso) {
                        consultarRegistro(vehiculosSeleccionados.get(0).getIdVehiculo(), "vehiculo");
                    } else {
                        Intent i = new Intent(this, RenderActivity.class);
                        Activity activity = this;
                        activity.startActivity(i);
                        activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    }
                }

            } else {
                tietBuscar.setText(valor);
                tietBuscar.setError("No corresponde a un vehículo registrado, vuelva a intentarlo");
                tietBuscar.requestFocus();

            }
        } else {
            tietBuscar.setText(valor);
            tietBuscar.setError("No se encontraron datos para este vehículo");
            tietBuscar.requestFocus();
        }
    }

    private void buscarEmpleado(String dni) {
        List<Empelado> empleado;

        empleado = empleadoViewModel.getEmpleadoByNfcId(dni);
        if (empleado == null || empleado.size() == 0) {
            empleado = empleadoViewModel.getEmpleadoByDni(dni);
        }
        if (empleado != null) {
            Gson gson = new Gson();
            Type marcarIngresoType = new TypeToken<ArrayList<String>>() {
            }.getType();
            ArrayList<String> marcarIngreso = gson.fromJson(usuario.getMarcarIngreso(), marcarIngresoType);
            if (empleado.size() > 0) {
                ArrayList<Empelado> empleadosSeleccionados = new ArrayList<>();
                for (Empelado emp : empleado) {
                    if (empresaAsEmpleadoViewModel.existeEmpreasAsEmpeladoAsync(idEmpresa, emp.getIdEmpleado())) {
                        empleadosSeleccionados.add(emp);
                    }
                }

                if (empleadosSeleccionados.size() == 0) {
                    tietBuscar.setError("No se encontraron datos para este empleado");
                    tietBuscar.requestFocus();
                    return;
                }

                if (empleadosSeleccionados.size() > 1) {
                    tvOpcional.animate()
                            .alpha(1.0f)
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    tvOpcional.setVisibility(View.VISIBLE);
                                }
                            });
                    listaOpciones.animate()
                            .alpha(1.0f)
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    listaOpciones.setVisibility(View.VISIBLE);
                                }
                            });
                    ArrayAdapter<String> adapter;
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
                    for (Empelado emp : empleadosSeleccionados) {
                        adapter.add(emp.getRazonSocial());

                    }
                    listaOpciones.setAdapter(adapter);
                    listaOpciones.setOnItemClickListener((parent, view, position, id) -> {

                        estadoContratista = empleadosSeleccionados.get(position).getMensajeGeneral();
                        urlFoto = empleadosSeleccionados.get(position).getUrlImagen();
                        estadoEmpelado = empleadosSeleccionados.get(position).getEstado();
                        colorTexto = empleadosSeleccionados.get(position).getColorTextoMensajeGeneral();
                        dato = gson.fromJson(empleadosSeleccionados.get(position).getDatosEmpleado(), Dato[].class);
                        boolean puedeMarcarIngreso = false;
                        if(marcarIngreso != null) {
                            for (String idMarcaIngreso : marcarIngreso) {
                                if (idEmpresa.equals(idMarcaIngreso)) {
                                    puedeMarcarIngreso = true;
                                    break;
                                }
                            }
                        }
                        if (puedeMarcarIngreso) {
                            consultarRegistro(empleadosSeleccionados.get(position).getIdEmpleado(), "empleado");
                        } else {
                            Intent i = new Intent(BuscarActivity.this, RenderActivity.class);
                            Activity activity = BuscarActivity.this;
                            activity.startActivity(i);
                            activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
                        }
                    });
                } else {
                    boolean puedeMarcarIngreso = false;
                    if(marcarIngreso != null){
                        for (String idMarcaIngreso : marcarIngreso) {
                            if (idEmpresa.equals(idMarcaIngreso)) {
                                puedeMarcarIngreso = true;
                                break;
                            }
                        }
                    }
                    estadoContratista = empleadosSeleccionados.get(0).getMensajeGeneral();
                    urlFoto = empleadosSeleccionados.get(0).getUrlImagen();
                    estadoEmpelado = empleadosSeleccionados.get(0).getEstado();
                    colorTexto = empleadosSeleccionados.get(0).getColorTextoMensajeGeneral();
                    dato = gson.fromJson(empleadosSeleccionados.get(0).getDatosEmpleado(), Dato[].class);
                    if (puedeMarcarIngreso) {
                        consultarRegistro(empleadosSeleccionados.get(0).getIdEmpleado(), "empleado");
                    } else {
                        Intent i = new Intent(this, RenderActivity.class);
                        Activity activity = this;
                        activity.startActivity(i);
                        activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    }
                }

            } else if (!dni.matches(".*[A-Z].*")) {
                tietBuscar.setText(dni);
                tietBuscar.setError("No corresponde a un empleado registrado, vuelva a intentarlo");
                tietBuscar.requestFocus();
            } else {
                Toast.makeText(this, "No se encontraron datos para este empleado", Toast.LENGTH_LONG).show();
            }

        } else {
            tietBuscar.setText(dni);
            tietBuscar.setError("No se encontraron datos para este empleado");
            tietBuscar.requestFocus();
        }

    }

    private void consultarRegistro(String idEmpleado, String tipoEntidad) {
        ControlDeIngresos registro = new ControlDeIngresos(idEmpleado, tipoEntidad, MainActivity.idUsuarioLogueado, sdf.format(new Date()),
                estadoEmpelado, "", "", "", idEmpresa);
        RequestAccionARegistrarEmpleado action = new RequestAccionARegistrarEmpleado(idEmpleado);
        Call<ResponseRegistroEmpleado> call = empleadoService.getAccionDeEmpleado( usuario.getTokenUsuario(), action);
        progressLayout.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ResponseRegistroEmpleado>() {
            @Override
            public void onResponse(Call<ResponseRegistroEmpleado> call, Response<ResponseRegistroEmpleado> response) {
                if (response.isSuccessful()) {
                    progressLayout.setVisibility(View.GONE);
                    if (response.body().getStatus()) {
                        showDiag(registro, response.body().getDataRegistro());
                    }
                } else if(response.code() == 401) {
                    updateTokenAndRetryAction(usuario.getUsuarioIdentificacion(), usuario.getUsuarioPassword(), idEmpleado, tipoEntidad);
                }else {
                    Toast.makeText(BuscarActivity.this, "Ocurrió un error, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                    progressLayout.setVisibility(View.GONE);
                    try {
                        System.out.println(response.errorBody().string());;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegistroEmpleado> call, Throwable t) {
                Toast.makeText(BuscarActivity.this, "Ocurrió un error, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                progressLayout.setVisibility(View.GONE);
            }
        });


       /* if (registro != null) {
            registro = controlDeIngresosViewModel.getRegistroConEntrada(idEmpleado, idEmpresa, "");
            if (registro != null) {
                registro.setEstadoEgreso(estadoEmpelado);
                registro.setFechaHoraSalida(sdf.format(new Date()));
                registro.setIdUsuarioEgreso(MainActivity.idUsuarioLogueado);
                ingresoEgreso = "Registrar Salida";
                showDiag(true, registro);
            } else {
                ControlDeIngresos nuevoIngreso = new ControlDeIngresos(idEmpleado, tipoEntidad, MainActivity.idUsuarioLogueado, sdf.format(new Date()), estadoEmpelado, "", "", "", idEmpresa);
                ingresoEgreso = "Registrar Entrada";
                showDiag(false, nuevoIngreso);
            }

        } else {
            //No existe debo generar un ingreso
            ControlDeIngresos nuevoIngreso = new ControlDeIngresos(idEmpleado, tipoEntidad, MainActivity.idUsuarioLogueado, sdf.format(new Date()), estadoEmpelado, "", "", "", idEmpresa);
            ingresoEgreso = "Registrar Entrada";
            showDiag(false, nuevoIngreso);
        }*/


    }

    private void updateTokenAndRetryAction(String username, String password, String idEmpleado, String tipoEntidad) {
        Call<ResponseLogin> call = loginService.postLogin(username, password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Data data = response.body().getData();

                    Usuario user = usuarioList.get(0);
                    user.setTokenUsuario(data.getBearer());
                    usuarioViewModel.updateUser(user);
                    usuarioList.clear();
                    usuarioList = usuarioViewModel.getUsuarios();
                    usuario = usuarioList.get(0);

                    consultarRegistro(idEmpleado, tipoEntidad);
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }

    private void inicializarViewModel() {
        usuarioViewModel = ViewModelProviders.of(BuscarActivity.this).get(UsuarioViewModel.class);
        empresaViewModel = ViewModelProviders.of(BuscarActivity.this).get(EmpresaViewModel.class);
        empleadoViewModel = ViewModelProviders.of(BuscarActivity.this).get(EmpleadoViewModel.class);
        vehiculoViewModel = ViewModelProviders.of(BuscarActivity.this).get(VehiculoViewModel.class);
        empresaAsEmpleadoViewModel = ViewModelProviders.of(BuscarActivity.this).get(EmpresaAsEmpleadoViewModel.class);
        empresaAsVehiculoViewModel = ViewModelProviders.of(BuscarActivity.this).get(EmpresaAsVehiculoViewModel.class);
        controlDeIngresosViewModel = ViewModelProviders.of(BuscarActivity.this).get(ControlDeIngresosViewModel.class);


    }

    private void linkVariables() {
        btnVolver = findViewById(R.id.btnCancelar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnEscanear = findViewById(R.id.btnEscanearQR);
        tietBuscar = findViewById(R.id.tietBuscar);
        tilBuscar = findViewById(R.id.tilBuscar);
        radioGroup = findViewById(R.id.radioGroup);
        rbEmpleado = findViewById(R.id.rbEmpleados);
        rbVeiculo = findViewById(R.id.rbVeiculos);
        tvNombreDeLaEmpresa = findViewById(R.id.tvNombreDeLaEmpresa);
        btnActualizarBD = findViewById(R.id.btnActualizarBD);
        tvFechaDeActualizacion = findViewById(R.id.tvFechaDeActualizacion);

        listaOpciones = findViewById(R.id.listaOpiones);
        tvOpcional = findViewById(R.id.tvOpcional);
        progressLayout = findViewById(R.id.progressLayout);

        inicializarValorHintEditText();
    }

    private void inicializarValorHintEditText() {
        radioGroup.check(R.id.rbEmpleados);
        tilBuscar.setHint(Constantes.CHECK_EMPLEADO);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                String idEscaneado = data.getStringExtra(Constantes.QR_RESULT);
                String tipo = data.getStringExtra((Constantes.QR_RESULT_TIPO));

                assert tipo != null;
                switch (tipo.toLowerCase()) {
                    case Constantes.TIPO_EMPLEADO:
                        assert idEscaneado != null;
                        rbEmpleado.setChecked(true);
                        buscarEmpleado(idEscaneado);
                        break;
                    case Constantes.TIPO_PROVEEDOR:
                    case Constantes.TIPO_VEHICULO:
                        rbVeiculo.setChecked(true);
                        buscarVehiculo(idEscaneado);
                        break;
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.CAMERA)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        startActivityForResult(new Intent(BuscarActivity.this, ScanQRCode.class), 1);
                    }
                }
            }
        }
    }

    private void showDiag(ControlDeIngresos registro, DataRegistro dataRegistro) {
        LinearLayout linearLayout, progressBarLy;
        ImageView ivFoto;
        TextView tvEstado, tvEstadoContratista;
        MaterialButton btnRegistrarIngresoEgreso;
        final View dialogView = View.inflate(this, R.layout.dialog, null);

        final Dialog dialog = new Dialog(this, R.style.MyAlertDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);

        linearLayout = dialog.findViewById(R.id.llVistaDeDatos);
        progressBarLy = dialog.findViewById(R.id.progress_bar_ly);
        ivFoto = dialog.findViewById(R.id.ivFoto);
        tvEstado = dialog.findViewById(R.id.tvEstado);
        tvEstadoContratista = dialog.findViewById(R.id.tvEstadoContratista);
        btnRegistrarIngresoEgreso = dialog.findViewById(R.id.btnRegistroIngesoEgreso);

        btnRegistrarIngresoEgreso.setText(dataRegistro.getMessage());
        btnRegistrarIngresoEgreso.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(dataRegistro.getColor())));
        btnRegistrarIngresoEgreso.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
        tvEstado.setText(estadoEmpelado);
        tvEstado.setPadding(0, 15, 0, 15);
        if (tvEstado.getText().equals("Inhabilitado")) {
            tvEstado.setBackgroundResource(R.drawable.bg_text_view_estado_inhabilitado);
            btnRegistrarIngresoEgreso.setVisibility(View.GONE);
        } else {
            tvEstado.setBackgroundResource(R.drawable.bg_text_view_estado_habilitado);
            btnRegistrarIngresoEgreso.setVisibility(View.VISIBLE);
        }
        tvEstadoContratista.setText(estadoContratista);
        tvEstadoContratista.setTextColor(Color.parseColor(colorTexto));

        if (BuscarActivity.rbEmpleado.isChecked()) {
            Glide.with(this)
                    .load(Constantes.URL_FOTO + urlFoto)
                    .placeholder(R.drawable.ic_worker)
                    .into(ivFoto);
        } else {
            Glide.with(this)
                    .load(Constantes.URL_FOTO + urlFoto)
                    .placeholder(R.drawable.ic_truck)
                    .into(ivFoto);
        }

        btnRegistrarIngresoEgreso.setOnClickListener(v -> {

            RequestRegistrarEmpleado registrarEmpleado = new RequestRegistrarEmpleado(registro.getIdEmpresa(), registro.getIdRegistro(),
                    usuario.getIdUsuario());
            registerEmpleado(progressBarLy, dialog, registrarEmpleado);
        });

        /*dialog.setOnDismissListener(dialog1 -> {
            if(updateRegister){
                controlDeIngresosViewModel.updateRegistro(registro);
            }else {
                controlDeIngresosViewModel.insertarRegistro(registro);
            }
        });*/

        Dato[] empleado = dato;

        int N = empleado.length;

        for (int i = 0; i < N; i++) {

            if (!empleado[i].getId().equals("***")) {
                LinearLayout rootLinear = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(50, 10, 0, 10);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params2.setMargins(0, 10, 0, 10);
                rootLinear.setOrientation(LinearLayout.HORIZONTAL);
                rootLinear.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                TextView rowTextView = new TextView(this);
                TextView rowTextView2 = new TextView(this);
                rowTextView.setText(empleado[i].getId().replace("CUIL", "RUT").replace("No es chofer", "") + " ");
                rowTextView2.setText(empleado[i].getValor());
                rowTextView.setLayoutParams(params);
                rowTextView2.setLayoutParams(params2);
                rowTextView.setTypeface(rowTextView.getTypeface(), Typeface.BOLD);
                rootLinear.addView(rowTextView);
                rootLinear.addView(rowTextView2);
                linearLayout.addView(rootLinear);
            }
        }

        dialog.setOnShowListener(dialogInterface -> revealShow(dialogView, true, null));

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();


        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    revealShow(dialogView, false, dialog);

                } catch (Exception ignored) {
                    System.out.println("Exception");
                }
            }
        };
        background.start();
    }

    private void registerEmpleado(LinearLayout progressBarLy, Dialog dialog, RequestRegistrarEmpleado registrarEmpleado) {
        Call<ResponseRegistroEmpleado> call = empleadoService.postRegistrarEmpleado(usuarioList.get(0).getTokenUsuario(), registrarEmpleado);

        // Show loading
        progressBarLy.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ResponseRegistroEmpleado>() {
            @Override
            public void onResponse(Call<ResponseRegistroEmpleado> call, Response<ResponseRegistroEmpleado> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(BuscarActivity.this, response.body().getDataRegistro().getMessage(), Toast.LENGTH_SHORT).show();
                    tietBuscar.setText(null);
                    progressBarLy.setVisibility(View.GONE);
                    dialog.dismiss();
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                        updateTokenAndRetry(usuario.getUsuarioIdentificacion(), usuario.getUsuarioPassword(), registrarEmpleado, progressBarLy, dialog);
                    } catch (IOException e) {
                        Toast.makeText(BuscarActivity.this, "Ocurrió un error, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                        progressBarLy.setVisibility(View.GONE);
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegistroEmpleado> call, Throwable t) {
                // TODO: show error message and hide loading
                progressBarLy.setVisibility(View.GONE);
                Toast.makeText(BuscarActivity.this, "Ocurrió un error, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                progressBarLy.setVisibility(View.GONE);
            }
        });
    }

    private void updateTokenAndRetry(String username, String password, RequestRegistrarEmpleado registrarEmpleado, LinearLayout progressBarLy, Dialog dialog) {
        Call<ResponseLogin> call = loginService.postLogin(username, password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Data data = response.body().getData();

                    Usuario user = usuarioList.get(0);
                    user.setTokenUsuario(data.getBearer());
                    usuarioViewModel.updateUser(user);
                    usuarioList.clear();
                    usuarioList = usuarioViewModel.getUsuarios();

                    registerEmpleado(progressBarLy, dialog, registrarEmpleado);
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }

    private void revealShow(View dialogView, boolean b, final Dialog dialog) {

        final View view = dialogView.findViewById(R.id.dialog);

        int w = view.getWidth();
        int h = view.getHeight();

        int endRadius = (int) Math.hypot(w, h);

        int cx = (int) (btnBuscar.getX() + (btnBuscar.getWidth() / 2));
        int cy = (int) (btnBuscar.getY()) + btnBuscar.getHeight() + 56;


        if (b) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, endRadius);

            view.setVisibility(View.VISIBLE);
            revealAnimator.setDuration(700);
            revealAnimator.start();

        } else {

            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);

                }
            });
            anim.setDuration(700);
            anim.start();
        }

    }
}
