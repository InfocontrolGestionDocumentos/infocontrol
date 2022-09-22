package com.app.infocontrol.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.infocontrol.R;
import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.commons.ImageSaver;
import com.app.infocontrol.commons.MyApp;
import com.app.infocontrol.data.room.Models.Empresa;
import com.app.infocontrol.ui.activity.BuscarActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyEmpresaRecyclerViewAdapter  extends RecyclerView.Adapter<MyEmpresaRecyclerViewAdapter.ViewHolder> implements Filterable {

    private Context ctx;
    private List<Empresa> mListEmpresa, mListEmpresaFull;

    public MyEmpresaRecyclerViewAdapter(Context context, List<Empresa> items) {
        mListEmpresa = items;
        mListEmpresaFull = new ArrayList<>(mListEmpresa);
        ctx = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_empresa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItem = mListEmpresa.get(position);
        if(holder.mItem.getActiva()== 1) {
            holder.tvNombreEmpresa.setText(holder.mItem.getNombre());
            String foto = holder.mItem.getLogo();

            if (!foto.equals("")) {
                String[] array = foto.split("/");
                String nombreImagen = array[array.length - 1];
                Bitmap bitmap = new ImageSaver(MyApp.getContext()).
                        setFileName(nombreImagen).
                        setDirectoryName(Constantes.DIR_EMPRESAS).
                        load();
                if (bitmap == null) {
                    Glide.with(ctx)
                            .load(Constantes.URL_FOTO + foto)
                            .placeholder(R.drawable.ic_factory)
                            .into(holder.ivLogoEmpresa);
                } else {
                    holder.ivLogoEmpresa.setImageBitmap(bitmap);
                }
            }


            holder.mView.setOnClickListener(v -> {
                Intent i = new Intent(ctx, BuscarActivity.class);
                i.putExtra(Constantes.KEY_IDEMPRESA, holder.mItem.getIdEmpresa());
                Activity activity = (Activity) ctx;
                activity.startActivity(i);
                activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
            });
        }
    }

    @Override
    public Filter getFilter() {
        return docentesFilter;
    }

    private Filter docentesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Empresa> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(mListEmpresaFull);
            } else {
                String filteredPattern = constraint.toString().toLowerCase().trim();

                for(Empresa empresa: mListEmpresaFull){
                    if(empresa.getNombre().toLowerCase().contains(filteredPattern)){
                        filteredList.add(empresa);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mListEmpresa.clear();
            mListEmpresa.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public int getItemCount() {
        return mListEmpresa.size();
    }

    public void setNuevaEmpresa(List<Empresa> empresa) {
        this.mListEmpresa = empresa;
        mListEmpresaFull = new ArrayList<>(this.mListEmpresa);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;

        final ImageView ivLogoEmpresa;
        final TextView tvNombreEmpresa;
        Empresa mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivLogoEmpresa = view.findViewById(R.id.ivEmpresa);
            tvNombreEmpresa = view.findViewById(R.id.tvNombreEmpresa);

        }
    }
}
