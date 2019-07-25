package com.example.formularylab;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.formularylab.Entidades.Formulas;
import com.example.formularylab.Utilidades.Utilidades;

import java.util.ArrayList;

public class Saved_Formulas extends AppCompatActivity {
    Button DeleteSQLite;
    ListView ListViewFormulas;
    ArrayList <String> ListInformation;
    ArrayList <Formulas> ListFormulas;
    AdminSQLiteOpenHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved__formulas);
        ListViewFormulas = (ListView) findViewById(R.id.lv_saved_formulas);
        DeleteSQLite = (Button) findViewById(R.id.bt_delete_formulas);

        conn = new AdminSQLiteOpenHelper(getApplicationContext(), "db_formulas", null, 1);


        ConsultBD();


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListInformation);
        ListViewFormulas.setAdapter(adapter);

        ListViewFormulas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Formulas formula = ListFormulas.get(position);
                int id = formula.getId();
                Bundle miBundle_formulas = new Bundle();
                Intent intent = null;
                if (id == 1 || id == 2 || id == 3 || id == 4 || id == 5 || id == 6 || id == 7 || id == 8 || id == 9) {
                    intent = new Intent(Saved_Formulas.this, Show_Formulas.class);
                    miBundle_formulas.putInt("Selected_Formula", id);
                    intent.putExtras(miBundle_formulas);
                    startActivity(intent);
                }

                //Toast.makeText(getApplicationContext(), "Id:" + id, Toast.LENGTH_LONG).show();

            }
        });

            DeleteSQLite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDatabase DataBase = conn.getReadableDatabase();
                    DataBase.delete(Utilidades.TABLA_FORMULAS,null,null);
                    DataBase.close();
                }
            });
    }

    private void ConsultBD() {
        SQLiteDatabase DataBase = conn.getReadableDatabase();
        Formulas formula=null;
        ListFormulas=new ArrayList<Formulas>();
        Cursor cursor = DataBase.rawQuery(" SELECT * FROM " +Utilidades.TABLA_FORMULAS,null);

        while(cursor.moveToNext()){
            formula=new Formulas();
            formula.setId(cursor.getInt(0));
            formula.setNombre(cursor.getString(1));
            ListFormulas.add(formula);
        }
        getList();
    }

    private void getList() {
        ListInformation=new ArrayList<String>();

        for(int i=0;i<ListFormulas.size();i++){
            ListInformation.add(ListFormulas.get(i).getId()+" - "+ListFormulas.get(i).getNombre());
        }

    }

}
