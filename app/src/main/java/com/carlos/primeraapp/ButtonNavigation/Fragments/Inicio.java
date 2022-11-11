package com.carlos.primeraapp.ButtonNavigation.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.carlos.primeraapp.HttpRequest.API;
import com.carlos.primeraapp.HttpRequest.ApiService;
import com.carlos.primeraapp.HttpRequest.Request.NoteSchema;
import com.carlos.primeraapp.HttpRequest.Response.Notes;
import com.carlos.primeraapp.HttpRequest.Response.Sql;
import com.carlos.primeraapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Inicio extends Fragment {
    //vista global
    View view;

    //instanciamos nuestra api de manera globgal
    API api = new API();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inicio, container, false);

        //funcion que recibe un titulo y un mensaje
        setNote("Titulo", "Mensaje");

        return view;
    }

    private void setNote(String stitle, String smessage) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.getURL())
                    .addConverterFactory((GsonConverterFactory.create()))
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            //instancia de noteSchema para mandar el body
            NoteSchema noteSchema = new NoteSchema(stitle, smessage);
            //cambiamos el metodo add note
            Call<Sql> call = apiService.addNote(noteSchema);
            call.enqueue(new Callback<Sql>() {
                @Override
                public void onResponse(Call<Sql> call, Response<Sql> response) {
                    if(!response.isSuccessful()){
                        Log.d("notes", "onResponse: " + response.code());
                        return;
                    } else{
                        // Obtenemos la respuesta del servidor
                        Sql sql = response.body();
                        Toast.makeText(getContext(), "Nota Agregada Exitosamente", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Sql> call, Throwable t) {
                    Log.d("notes", "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("notes", "Catch: " + e.getMessage());
        }
    }
}