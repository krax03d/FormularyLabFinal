package com.example.formularylab;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formularylab.Entidades.Formulas;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity  {
    private int seleccion_subcategory;
    private int seleccion_category;
    private int scan_qr_result;
    private Spinner spinner1;
    private TextView tv1, tv2;
    private boolean PrimeraVez = true;
    private ListView ListTrigo;
    private Button qr;
    private Button Fav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tvPrincipal);
        tv2 = (TextView) findViewById(R.id.tv2Principal);
        spinner1 = (Spinner) findViewById(R.id.spinnerPrincipal);
        ListTrigo = (ListView) findViewById(R.id.lv_main);
        qr = (Button) findViewById(R.id.bt_qr);
        Fav=(Button) findViewById(R.id.bt_saved_formulas_main);
        AdminSQLiteOpenHelper conn= new AdminSQLiteOpenHelper( this,"db_formulas",null,1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerOpciones, R.layout.spinner_item_size);
        spinner1.setAdapter(adapter);
        //ArrayList<String>options_main=new ArrayList<String>();
        //options_main.add("Seleccione");
        //options_main.add("Calculo");
        //options_main.add("Fisica");
        //options_main.add("Trigonometria");
        //ArrayAdapter<CharSequence> adapterSpinner=new ArrayAdapter(this,android.R.layout.simple_spinner_item,options_main);
        //spinner1.setAdapter(adapterSpinner);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccion_category = position;
                //Intent category=null;
                //Bundle miBundle_category=new Bundle();
                switch (position) {
                    case 0:
                        // Toast.makeText(getApplicationContext(),"Seleccione Alguna Opción",Toast.LENGTH_LONG).show();

                        ListTrigo.setAdapter(null);
                        break;
                    case 1:

                        ArrayAdapter<CharSequence> adapterListView_Calcu = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Calcu_options, R.layout.listview_item_size);
                        ListTrigo.setAdapter(adapterListView_Calcu);
                        //Toast.makeText(getApplicationContext(),"Selecciono 1",Toast.LENGTH_LONG).show();
                        break;
                    case 2:

                        //Toast.makeText(getApplicationContext(),"Selecciono 2",Toast.LENGTH_LONG).show();
                        ArrayAdapter<CharSequence> adapterListView_Fisica = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Fisica_options, R.layout.listview_item_size);
                        ListTrigo.setAdapter(adapterListView_Fisica);

                        break;
                    case 3:

                        //Toast.makeText(getApplicationContext(),"Selecciono 3",Toast.LENGTH_LONG).show();
                        ArrayAdapter<CharSequence> adapterListView_Trigo = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Trigo_options, R.layout.listview_item_size);
                        ListTrigo.setAdapter(adapterListView_Trigo);
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fav = new Intent(MainActivity.this, Saved_Formulas.class);
                startActivity(fav);
            }
        });



        qr.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {

                    Intent Scann_QR = new Intent(MainActivity.this, Scanner_QR.class);
                    startActivity(Scann_QR);
            }
        });


        ListTrigo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccion_subcategory = position;

                Intent sub_categories = null;
                Bundle miBundle_subcategory = new Bundle();

                switch (seleccion_subcategory) {
                    case 0:
                        // Toast.makeText(getApplicationContext(),"Selecciono la opcion 0",Toast.LENGTH_LONG).show();
                        sub_categories = new Intent(MainActivity.this, Categories_One.class);

                        miBundle_subcategory.putInt("Selected_Subcategory", seleccion_subcategory);
                        miBundle_subcategory.putInt("Selected_Category", seleccion_category);
                        sub_categories.putExtras(miBundle_subcategory);

                        startActivity(sub_categories);
                        break;
                    case 1:
                        //  Toast.makeText(getApplicationContext(),"Selecciono la opcion 1",Toast.LENGTH_LONG).show();
                        sub_categories = new Intent(MainActivity.this, Categories_One.class);
                        miBundle_subcategory.putInt("Selected_Subcategory", seleccion_subcategory);
                        miBundle_subcategory.putInt("Selected_Category", seleccion_category);
                        sub_categories.putExtras(miBundle_subcategory);


                        startActivity(sub_categories);
                        break;
                    case 2:
                        //Toast.makeText(getApplicationContext(),"Selecciono la opcion 2",Toast.LENGTH_LONG).show();
                        sub_categories = new Intent(MainActivity.this, Categories_One.class);
                        miBundle_subcategory.putInt("Selected_Subcategory", seleccion_subcategory);
                        miBundle_subcategory.putInt("Selected_Category", seleccion_category);
                        sub_categories.putExtras(miBundle_subcategory);


                        startActivity(sub_categories);
                        break;
                }

            }
        });



        }



    }


