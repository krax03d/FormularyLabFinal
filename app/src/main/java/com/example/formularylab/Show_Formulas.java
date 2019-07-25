package com.example.formularylab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.formularylab.Entidades.Formulas;

public class Show_Formulas extends AppCompatActivity {
    ImageView showFormulas;
    int seleccion_formulas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__formulas);
        showFormulas=(ImageView) findViewById(R.id.iv_show_formulas);
        Bundle miBundle= this.getIntent().getExtras();
        seleccion_formulas=miBundle.getInt("Selected_Formula");
        searchFormulas();
    }
    private void searchFormulas(){
        int id;
        switch (seleccion_formulas){

            case 1:
                id = getResources().getIdentifier("derivar", "drawable", getPackageName());
                showFormulas.setImageResource(id);
                break;
            case 2:
                id = getResources().getIdentifier("integrales2", "drawable", getPackageName());
                showFormulas.setImageResource(id);
                break;
            case 3:
                id = getResources().getIdentifier("limitles", "drawable", getPackageName());
                showFormulas.setImageResource(id);
                break;
            case 4:
                id = getResources().getIdentifier("move_parabolic", "drawable", getPackageName());
                showFormulas.setImageResource(id);
                break;
            case 5:
                id = getResources().getIdentifier("move_rectiline_uniform", "drawable", getPackageName());
                showFormulas.setImageResource(id);
                break;
            case 6:
                id = getResources().getIdentifier("trigo_coseno", "drawable", getPackageName());
                showFormulas.setImageResource(id);
                break;
            case 7:
                id = getResources().getIdentifier("trigo_seno", "drawable", getPackageName());
                showFormulas.setImageResource(id);
                break;
            case 8:
                id = getResources().getIdentifier("trigo_tangente", "drawable", getPackageName());
                showFormulas.setImageResource(id);
                break;
                case 9:
                id = getResources().getIdentifier("vectors", "drawable", getPackageName());
                showFormulas.setImageResource(id);
                break;
        }
    }
}
