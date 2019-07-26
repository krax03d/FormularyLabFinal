package com.example.formularylab;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.formularylab.Utilidades.Utilidades;

import java.net.URL;
import java.net.URLConnection;

import static java.lang.System.load;


public class Fragment_Derivar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button navigator;
    Button save;
    View vista;
    ImageView imagen;
    RequestQueue request;

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
        imagen=(ImageView)vista.findViewById(R.id.iv_derivation_image);
        //request = Volley.newRequestQueue(getContext());
        if(compruebaConexion(getContext())==true){
            LoadImagen();
        }else{
            imagen=null;
            Toast.makeText(getContext(),"Sin Conexión",Toast.LENGTH_LONG).show();
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SaveFormula();
                LoadImagen();
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
    private void LoadWebService(){
    String url="http://192.168.100.115/derivar.PNG";
        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imagen.setImageBitmap(response);
                    }
                },0,0,ImageView.ScaleType.CENTER,null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Toast.makeText(getContext(),"Error al cargar la imagen",Toast.LENGTH_LONG).show();
            }
        });
        request.add(imageRequest);
    }
    private void LoadImagen(){
        Glide.with(this)
        .load("http://192.168.100.116:80/derivar.PNG")
        .into(imagen);

    }

    public static boolean compruebaConexion(Context context) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }
}
