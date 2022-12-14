package com.carlos.primeraapp.ButtonNavigation.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.carlos.primeraapp.Adapters.NotesAdapter;
import com.carlos.primeraapp.HttpRequest.API;
import com.carlos.primeraapp.HttpRequest.ApiService;
import com.carlos.primeraapp.HttpRequest.Request.NoteSchema;
import com.carlos.primeraapp.HttpRequest.Response.Notes;
import com.carlos.primeraapp.HttpRequest.Response.Sql;
import com.carlos.primeraapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Array;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class Inicio extends Fragment {
    //vista global
    //listview
    ListView listViewNotes;
    View view;
    EditText tituloNote;
    EditText mensajeNote;
    Button botonMandarNota;
    //instanciamos nuestra api de manera globgal
    API api = new API();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        //LISTVIEW
        listViewNotes = view.findViewById(R.id.listViewNotas);

        tituloNote = view.findViewById(R.id.tituloNote);
        mensajeNote = view.findViewById(R.id.mensajeNote);

        //boton
        botonMandarNota = view.findViewById(R.id.botonMandarNota);

        //funcion insertar
        botonMandarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNote(tituloNote.getText().toString(), mensajeNote.getText().toString());
            }
        });
        //funcion que recibe un titulo y un mensaje
        //setNote("Titulo", "Mensaje");
        getNotes();

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


    private void getNotes() {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.getURL())
                    .addConverterFactory((GsonConverterFactory.create()))
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            //cambiamos el metodo get notes
            Call<Notes[]> call = apiService.getNotes();
            call.enqueue(new Callback<Notes[]>() {
                @Override
                public void onResponse(Call<Notes[]> call, Response<Notes[]> response) {
                    if(!response.isSuccessful()){
                        Log.d("notes", "onResponse: " + response.code());
                        return;
                    } else{
                        // Obtenemos la respuesta del servidor
                        Notes[] notes = response.body();
                        Toast.makeText(getContext(), "Checa la consola", Toast.LENGTH_SHORT).show();
                        /*for (int i = 0; i < notes.length; i++) {
                            Log.d("notes", notes[i].getTitle());
                        }*/
                        if (notes.length>0) {
                            //hay notas

                            //creamos instancia y mandamos los parametros necesarios
                            NotesAdapter notesAdapter = new NotesAdapter(notes, getActivity(), api);
                            //se lo anadimos a la lista
                            listViewNotes.setAdapter(notesAdapter);
                        }else{
                            // no hay notas
                            listViewNotes.setAdapter(null);
                        }
                    }

                }

                @Override
                public void onFailure(Call<Notes[]> call, Throwable t) {
                    Log.d("notes", "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("notes", "Catch: " + e.getMessage());
        }
    }
}


