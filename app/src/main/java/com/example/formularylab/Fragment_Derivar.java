package com.example.formularylab;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.formularylab.Utilidades.Utilidades;


public class Fragment_Derivar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button navigator;
    Button save;
    View vista;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_Derivar() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_Derivar newInstance(String param1, String param2) {
        Fragment_Derivar fragment = new Fragment_Derivar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista=inflater.inflate(R.layout.fragment_fragment__derivar, container, false);
        navigator=(Button) vista.findViewById(R.id.bt_derivation_more);
        save=(Button) vista.findViewById(R.id.bt_derivation_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveFormula();
            }
        });




        navigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"El evento",Toast.LENGTH_LONG).show();
                String page_web="https://es.wikipedia.org/wiki/Derivada";
                Intent web_navigator=null;
                Bundle miBundle_web=new Bundle();
                web_navigator=new Intent(getActivity(),navigator_web.class);
                miBundle_web.putString("navigator",page_web);
                web_navigator.putExtras(miBundle_web);
                startActivity(web_navigator);
            }
        });
        return(vista);
    }

    private void SaveFormula() {
        String imagen = "Derivadas";
        int id=1;
        AdminSQLiteOpenHelper conn= new AdminSQLiteOpenHelper( getContext(),"db_formulas",null,1);
        SQLiteDatabase DataBase = conn.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE, imagen);
        values.put(Utilidades.CAMPO_ID,id);
        Long idResultante=DataBase.insert(Utilidades.TABLA_FORMULAS,Utilidades.CAMPO_ID,values);
        Toast.makeText(getContext(),"Guardado Exitosamente",Toast.LENGTH_LONG).show();
        DataBase.close();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}