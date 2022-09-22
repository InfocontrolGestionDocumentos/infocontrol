package com.app.infocontrol.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.infocontrol.R;
import com.app.infocontrol.commons.MyApp;
import com.app.infocontrol.commons.VerificarEstadoDeConexion;
import com.app.infocontrol.data.pojo.response.ResponeLogin.Data;
import com.app.infocontrol.data.pojo.response.ResponeLogin.ResponseLogin;
import com.app.infocontrol.data.pojo.response.ResponeLogin.UserData;
import com.app.infocontrol.data.repository.EmpresaRepository;
import com.app.infocontrol.data.retrofit.clients.LoginClient;
import com.app.infocontrol.data.retrofit.services.LoginService;
import com.app.infocontrol.data.room.Models.Usuario;
import com.app.infocontrol.data.viewmodel.EmpleadoViewModel;
import com.app.infocontrol.data.viewmodel.UsuarioViewModel;
import com.app.infocontrol.data.viewmodel.VehiculoViewModel;
import com.app.infocontrol.ui.fragment.RecuperarPassFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText tvCuil, tvPassword;
    private MaterialButton btnLogin;
    private CheckBox checkRemember;
    private TextView tvRecuperarPass,tvInicioSesion;
    private ProgressBar pbInicioSesion;

    private LoginClient loginCliente;
    private LoginService loginService;

    private ImageView ivLogo;

    private UsuarioViewModel usuarioViewModel;

    private List<Usuario> usuariosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel.class);
        usuariosList = usuarioViewModel.getUsuarios();
        boolean recordarUsuario = false;
        PreferenceManager.getDefaultSharedPreferences(getBaseContext()).
                edit().clear().apply();

        if(usuariosList != null && usuariosList.size() != 0){
            recordarUsuario = usuariosList.get(0).isRecordarUsuario();
        }

        if(!recordarUsuario) {
            setContentView(R.layout.activity_login);
            initServiceLogin();
            linkVariables();
            /*23297100009 - 27256495150*/
            /*tvCuil.setText("27256495150");
            tvPassword.setText("1234");*/

            tvRecuperarPass.setOnClickListener(v -> {
//              startActivity(new Intent(this, RecuperarPassActivity.class));
                RecuperarPassFragment dialogFragment =new RecuperarPassFragment();
                dialogFragment.show(this.getSupportFragmentManager(),"RecuperarPassFragment");
            });

            tvPassword.setOnEditorActionListener((v, actionId, event) -> {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                assert inputMethodManager != null;
                inputMethodManager.hideSoftInputFromWindow(tvPassword.getWindowToken(), 0);
                return true;
            });

            btnLogin.setOnClickListener(v -> {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                assert inputMethodManager != null;
                inputMethodManager.hideSoftInputFromWindow(tvPassword.getWindowToken(), 0);
                if(VerificarEstadoDeConexion.isNetDisponible(this)) {
                    if (Objects.requireNonNull(tvCuil.getText()).toString().isEmpty()) {
                        tvCuil.setError("Este campo es obligatorio");
                        tvCuil.requestFocus();
                    } else if (Objects.requireNonNull(tvPassword.getText()).toString().isEmpty()) {
                        tvPassword.setError("Este campo es obligatorio");
                        tvPassword.requestFocus();
                    } else {
                        String usuario = tvCuil.getText().toString().trim();
                        String pass = tvPassword.getText().toString();
                        validateLogin(usuario, pass);
                    }
                }else
                    Toast.makeText(this, "Verifique su conexión a internet", Toast.LENGTH_SHORT).show();
            });

        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void validateLogin(String username, String pass) {

        mostrarProgreso();
        Call<ResponseLogin> call = loginService.postLogin(username,pass);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NonNull Call<ResponseLogin> call, @NonNull Response<ResponseLogin> response) {
                ocultarProgreso();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Data data = response.body().getData();
                    UserData userData = data.getUserData();
                    Gson gson = new Gson();
                    String marcarIngreso = gson.toJson(userData.getMarcarIngreso());
                    Usuario usuario = new Usuario(userData.getIdUsuarios(),userData.getNombre(),username,pass,data.getBearer(),
                            checkRemember.isChecked(), marcarIngreso);
                    if(!usuarioViewModel.existe(usuario.getIdUsuario())) {
                        new EmpleadoViewModel(MyApp.getInstance()).eliminarEmpleados();
                        new EmpresaRepository(MyApp.getInstance()).eliminarEmpresas();
                        new VehiculoViewModel(MyApp.getInstance()).eliminarVehiculos();
                        usuarioViewModel.deleteAll();
                        usuarioViewModel.inserUser(usuario); // Almaceno los datos del usuario de forma local
                    }else {
                        usuarioViewModel.deleteAll();
                        usuarioViewModel.inserUser(usuario);
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseLogin> call,@NonNull Throwable t) {
                ocultarProgreso();
                Toast.makeText(getApplicationContext(), "Ocurrió un error, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ocultarProgreso() {
        btnLogin.setEnabled(true);
        tvInicioSesion.setVisibility(View.GONE);
        pbInicioSesion.setVisibility(View.GONE);
    }

    private void mostrarProgreso() {
        btnLogin.setEnabled(false);
        tvInicioSesion.setVisibility(View.VISIBLE);
        pbInicioSesion.setVisibility(View.VISIBLE);
    }

    private void initServiceLogin() {
        loginCliente = LoginClient.getInstance();
        loginService = loginCliente.getLoginService();
    }

    private void linkVariables() {
        ivLogo = findViewById(R.id.imageView2);
        tvInicioSesion = findViewById(R.id.tvInicioSesion);
        tvCuil = findViewById(R.id.cuilTV);
        tvPassword = findViewById(R.id.passwordTV);
        btnLogin = findViewById(R.id.btnLogin);
        checkRemember = findViewById(R.id.remembermeCheck);
        tvRecuperarPass = findViewById(R.id.tvRecuperarPass);
        pbInicioSesion = findViewById(R.id.pbInicioSesion);
    }
}
