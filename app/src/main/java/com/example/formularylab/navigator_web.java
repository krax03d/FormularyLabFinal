package com.example.formularylab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class navigator_web extends AppCompatActivity {
    private WebView Page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_web);

        Page=(WebView) findViewById(R.id.web_navigator_view);
        Bundle miBundle= this.getIntent().getExtras();
        String mi_navigator_view=miBundle.getString("navigator");
        Page.getSettings().setJavaScriptEnabled(true);
        Page.setWebViewClient(new WebViewClient());
        Page.loadUrl(mi_navigator_view);
    }
}
