package com.example.formularylab;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Categories_One extends AppCompatActivity implements Fragment_Derivar.OnFragmentInteractionListener,
        Fragment_Integrar.OnFragmentInteractionListener,
        Fragment_Vectores.OnFragmentInteractionListener,
        Fragment_Limitles.OnFragmentInteractionListener,
        Fragment_move_rectiline.OnFragmentInteractionListener,
        Fragment_Move_Parabolic.OnFragmentInteractionListener,
        Fragment_Trigo_Seno.OnFragmentInteractionListener,
        Fragment_Trigo_Coseno.OnFragmentInteractionListener,
        Fragment_Trigo_Tangente.OnFragmentInteractionListener{

    TextView tv1;
    TextView tv2;
    Fragment_Derivar fragment_derivar;
    Fragment_Integrar fragment_integrar;
    Fragment_Vectores fragment_vectores;
    Fragment_Limitles fragment_limitles;
    Fragment_move_rectiline fragment_move_rectiline;
    Fragment_Move_Parabolic fragment_move_parabolic;
    Fragment_Trigo_Seno fragment_trigo_seno;
    Fragment_Trigo_Coseno fragment_trigo_coseno;
    Fragment_Trigo_Tangente fragment_trigo_tangente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories__one);
        fragment_derivar= new Fragment_Derivar();
        fragment_integrar= new Fragment_Integrar();
        fragment_vectores=new Fragment_Vectores();
        fragment_limitles=new Fragment_Limitles();
        fragment_move_rectiline=new Fragment_move_rectiline();
        fragment_move_parabolic=new Fragment_Move_Parabolic();
        fragment_trigo_seno=new Fragment_Trigo_Seno();
        fragment_trigo_coseno=new Fragment_Trigo_Coseno();
        fragment_trigo_tangente=new Fragment_Trigo_Tangente();
        //Recibimos el valor de la sub_categoria
        //tv1=(TextView) findViewById(R.id.tv_Cal);
        //tv2=(TextView) findViewById(R.id.tv_cal2);
       Bundle miBundle= this.getIntent().getExtras();
        int seleccion_subcategory=miBundle.getInt("Selected_Subcategory");
        int seleccion_category=miBundle.getInt("Selected_Category");


          //tv1.setText("Escogio: "+seleccion_subcategory);
          //tv2.setText("Aver ahora"+seleccion_category);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
         if(seleccion_category==1) {
                switch (seleccion_subcategory) {
                     case 0:
                        transaction.add(R.id.frame_fragments, fragment_derivar);
                          break;
                    case 1:
                        transaction.add(R.id.frame_fragments, fragment_integrar);
                          break;
                    case 2:
                        transaction.add(R.id.frame_fragments, fragment_limitles);
                          break;
                    }
                transaction.commit();
                } if(seleccion_category==2){
               // Toast.makeText(getApplicationContext(),"Si sirve",Toast.LENGTH_LONG).show();
             switch (seleccion_subcategory) {
                     case 0:
                        transaction.add(R.id.frame_fragments, fragment_vectores);
                        break;
                 case 1:
                     transaction.add(R.id.frame_fragments, fragment_move_rectiline);
                     break;
                 case 2:
                     transaction.add(R.id.frame_fragments, fragment_move_parabolic);
                     break;

                }
                transaction.commit();
            }
            if(seleccion_category==3){
               // Toast.makeText(getApplicationContext(),"Si sirve",Toast.LENGTH_LONG).show();
                switch (seleccion_subcategory) {
                    case 0:
                        transaction.add(R.id.frame_fragments, fragment_trigo_seno);
                        break;
                    case 1:
                        transaction.add(R.id.frame_fragments, fragment_trigo_coseno);
                        break;
                    case 2:
                        transaction.add(R.id.frame_fragments, fragment_trigo_tangente);
                        break;

                }
                transaction.commit();
            }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
