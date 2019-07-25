package com.example.formularylab;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class Scanner_QR extends AppCompatActivity {

    public TextView Show_Scann;
    private Button qr;
    private ZBarScannerView vistScann;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner__qr);
        qr = (Button) findViewById(R.id.bt_scanQR);
        Show_Scann = (TextView) findViewById(R.id.tv_scann_result);
        configureButtonReader();

    }

        private void configureButtonReader() {

            qr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new IntentIntegrator(Scanner_QR.this).initiateScan();
                }
            });
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            final IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            handleResult(scanResult);
        }

        private void handleResult(IntentResult scanResult) {
            if (scanResult != null) {
                updateUITextViews(scanResult.getContents(), scanResult.getFormatName());
            } else {
                Toast.makeText(this, "No se ha leído nada :(", Toast.LENGTH_SHORT).show();
            }
        }

        private void updateUITextViews(String scan_result, String scan_result_format) {
            ((TextView)findViewById(R.id.tv_scann_result)).setText(scan_result_format);
            final TextView tvResult = (TextView)findViewById(R.id.tv_scann_result);
            tvResult.setText(scan_result);
            Linkify.addLinks(tvResult, Linkify.ALL);
        }
    }