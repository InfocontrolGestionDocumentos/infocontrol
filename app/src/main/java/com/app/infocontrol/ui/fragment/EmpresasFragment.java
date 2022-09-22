package com.app.infocontrol.ui.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.infocontrol.R;
import com.app.infocontrol.commons.MyApp;
import com.app.infocontrol.commons.VerificarEstadoDeConexion;
import com.app.infocontrol.data.room.Models.Empresa;
import com.app.infocontrol.data.viewmodel.EmpresaViewModel;
import com.app.infocontrol.ui.adapter.MyEmpresaRecyclerViewAdapter;
import com.app.infocontrol.ui.fragment.dialogs.LoadingDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmpresasFragment extends Fragment {

    /*  Filtrar por medio del buscador*/
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    /***/

    /*  Variables locales*/
    private List<Empresa> empresaList;
    private RecyclerView recyclerView;
    private MyEmpresaRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    /***/

    /*  PullRefresh*/
    private SwipeRefreshLayout swipeRefreshLayout;

    /*  ViewModels  */
    private EmpresaViewModel empresaViewModel;

    public EmpresasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresas, container, false);
        empresaList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewEmpresas);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {

            swipeRefreshLayout.setRefreshing(false);
            if (VerificarEstadoDeConexion.isNetDisponible(getContext())) {

                /*  Show Dialog Fragmen to Actualice data*/

                LoadingDialogFragment dialogFragment = new LoadingDialogFragment();
                dialogFragment.setCancelable(false);
                dialogFragment.show(getActivity().getSupportFragmentManager(),"LoadingDialogFragment");


            } else {
                Toast.makeText(MyApp.getContext(), "Verifique su conexión a internet", Toast.LENGTH_SHORT).show();
            }
        });
        layoutManager = new LinearLayoutManager(getActivity());
        loadDataEmpresas();
        return view;
    }

    private void loadDataEmpresas() {
        empresaList = new ArrayList<>();

        adapter = new MyEmpresaRecyclerViewAdapter(
                getActivity(),
                empresaList
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        lanzarViewModel();
    }

    private void lanzarViewModel() {
        empresaViewModel = ViewModelProviders.of(getActivity()).get(EmpresaViewModel.class);
        empresaViewModel.getAllEmpresas().observe(getActivity(), empresaList -> adapter.setNuevaEmpresa(empresaList));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.filtrado_actualizacion, menu);
        MenuItem buscarEmpresa = menu.findItem(R.id.buscarEmpresa);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (buscarEmpresa != null) {
            searchView = (SearchView) buscarEmpresa.getActionView();
        }
        if (searchView != null) {
            searchView.setQueryHint("Nombre de la empresa");
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buscarEmpresa:
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }


}
