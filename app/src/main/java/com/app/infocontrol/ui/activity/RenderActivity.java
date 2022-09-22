package com.app.infocontrol.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.infocontrol.R;
import com.app.infocontrol.commons.Constantes;
import com.app.infocontrol.commons.Empleado.Dato;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class RenderActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ImageView ivFoto;
    private TextView tvEstado,tvEstadoContratista;
    private MaterialButton btnVolver;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_render);
        linkVariables();
        Dato[] empleado = BuscarActivity.dato;
        tvEstado.setText(BuscarActivity.estadoEmpelado);
        tvEstado.setPadding(0,15,0,15);
        if(tvEstado.getText().equals("Inhabilitado")){
            tvEstado.setBackgroundResource(R.drawable.bg_text_view_estado_inhabilitado);
        }else {
            tvEstado.setBackgroundResource(R.drawable.bg_text_view_estado_habilitado);
        }
        tvEstadoContratista.setText(BuscarActivity.estadoContratista);
        tvEstadoContratista.setTextColor(Color.parseColor(BuscarActivity.colorTexto));

        if(BuscarActivity.rbEmpleado.isChecked()){
            Glide.with(this)
                    .load(Constantes.URL_FOTO + BuscarActivity.urlFoto)
                    .placeholder(R.drawable.ic_worker)
                    .into(ivFoto);
        }else {
            Glide.with(this)
                    .load(Constantes.URL_FOTO + BuscarActivity.urlFoto)
                    .placeholder(R.drawable.ic_truck)
                    .into(ivFoto);
        }


        int N = empleado.length;

        for (int i = 0; i < N; i++) {

            if(!empleado[i].getId().equals("***")) {
                LinearLayout rootLinear = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(450, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(50, 10, 0, 10);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params2.setMargins(0, 10, 0, 10);
                rootLinear.setOrientation(LinearLayout.HORIZONTAL);
                rootLinear.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                TextView rowTextView = new TextView(this);
                TextView rowTextView2 = new TextView(this);
                rowTextView.setText(empleado[i].getId() + " ");
                rowTextView.setElegantTextHeight(true);
                rowTextView.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                rowTextView.setSingleLine(false);
                rowTextView2.setText(empleado[i].getValor());
                rowTextView.setLayoutParams(params);
                rowTextView2.setLayoutParams(params2);
                rowTextView.setTypeface(rowTextView.getTypeface(), Typeface.BOLD);
                rootLinear.addView(rowTextView);
                rootLinear.addView(rowTextView2);
                linearLayout.addView(rootLinear);
            }
        }

        btnVolver.setOnClickListener( v -> onBackPressed());
    }

    private void linkVariables() {
        linearLayout = findViewById(R.id.llVistaDeDatos);
        ivFoto = findViewById(R.id.ivFoto);
        tvEstado = findViewById(R.id.tvEstado);
        tvEstadoContratista = findViewById(R.id.tvEstadoContratista);
        btnVolver = findViewById(R.id.btnVolverRender);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }
}
