package com.app.infocontrol.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.app.infocontrol.R;
import com.app.infocontrol.commons.MyApp;

public class RecuperarPassFragment extends DialogFragment {
    private WebView webView;
    private ProgressDialog progressDialog;

    public RecuperarPassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        this.setExitTransition(slide);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recuperar_pass, container, false);

        webView = root.findViewById(R.id.webView);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando..."); // Setting Message
        progressDialog.setTitle("Infocontrol"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show();
        progressDialog.setCancelable(false);

        String url = "https://www.infocontrol.com.ar/web/usuarios/recuperar_contrasena?app=1";
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final WebSettings ajustesVisorWeb = webView.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                Toast.makeText(MyApp.getContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(url);
        return root;
    }

}
