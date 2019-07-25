package com.example.formularylab;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

import static android.support.constraint.Constraints.TAG;

public class Scan_QR_Method extends Activity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        final String code =rawResult.getContents(); // Prints scan results
        final String format=rawResult.getBarcodeFormat().getName(); // Prints the scan format (qrcode, pdf417 etc.)
        final String full_message="Content: "+code+" Format: "+format;
        //showMessageDialog(full_message);

        Scanner_QR scanner_qr=new Scanner_QR();
        scanner_qr.Show_Scann.setText(""+code);
        //Toast.makeText(this,""+code,Toast.LENGTH_LONG).show();





        // If you would like to resume scanning, call this method below:

    }

}

