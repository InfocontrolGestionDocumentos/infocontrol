package com.app.infocontrol.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.app.infocontrol.R;
import com.app.infocontrol.commons.MyApp;
import com.app.infocontrol.commons.VerificarEstadoDeConexion;
import com.app.infocontrol.data.room.Models.Usuario;
import com.app.infocontrol.data.viewmodel.EmpleadoViewModel;
import com.app.infocontrol.data.viewmodel.EmpresaViewModel;
import com.app.infocontrol.data.viewmodel.UsuarioViewModel;
import com.app.infocontrol.data.viewmodel.VehiculoViewModel;
import com.app.infocontrol.ui.fragment.EmpresasFragment;
import com.app.infocontrol.ui.fragment.dialogs.LoadingDialogFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;

    private ConstraintLayout headerDrawer;

    private TextView tvNombreUsuario;

    //Fragments
    private Fragment fragment = null;

    // ViewModel and UserData
    private UsuarioViewModel usuarioViewModel;
    /*private Usuario usuario;*/
    private List<Usuario> usuarios;

    public static String idUsuarioLogueado;





    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel.class);
        usuarios = usuarioViewModel.getUsuarios();
        idUsuarioLogueado = usuarios.get(0).getIdUsuario();
        setContentView(R.layout.activity_main);
        linkRecursos();

        if(VerificarEstadoDeConexion.isNetDisponible(this)) {
            LoadingDialogFragment dialogFragment = new LoadingDialogFragment();
            dialogFragment.setCancelable(false);
            dialogFragment.show(getSupportFragmentManager(), "LoadingDialogFragment");

        }

        //usuarioViewModel.getUserById(SharedPreferenceManager.getSomeStringValue(Constantes.ID_USUARIO));
        tvNombreUsuario.setText("Hola " + usuarios.get(0).getNombreUsuario());
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        cambiarFragment(new EmpresasFragment(), navigationView.getMenu().getItem(0));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    @SuppressLint("WrongViewCast")
    private void linkRecursos() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        View headerView = navigationView.getHeaderView(0);
        tvNombreUsuario = headerView.findViewById(R.id.tvNombreUsuario);

        headerDrawer = headerView.findViewById(R.id.header_layout);

        AnimationDrawable animationDrawable = (AnimationDrawable) headerDrawer.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();


    }

    private void cambiarFragment(Fragment fragment, MenuItem item) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean fragmentTransaccion = false;

        switch (id) {
            case R.id.nav_empresas:

                fragment = new EmpresasFragment();
                fragmentTransaccion = true;
                break;
            case R.id.nav_cerrar_sesion:
                cerrarSesion();
                break;
        }

        if (fragmentTransaccion) {
            cambiarFragment(fragment, item);
        }

        return true;
    }

    private void cerrarSesion() {
        new EmpleadoViewModel(MyApp.getInstance()).eliminarEmpleados();
        new EmpresaViewModel(MyApp.getInstance()).eliminarEmpresas();
        new VehiculoViewModel(MyApp.getInstance()).eliminarVehiculos();
        usuarioViewModel.deleteAll();
        /*SharedPreferenceManager.clearSharedPreferences();*/
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!usuarios.get(0).isRecordarUsuario()){
            usuarioViewModel.deleteAll();
        }
      /*  if (!SharedPreferenceManager.getSomeBooleanValue(Constantes.RECORDAR_USUARIO)) {
            usuarioViewModel.deleteAll();
            SharedPreferenceManager.clearSharedPreferences();
        }*/
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
