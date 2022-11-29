package com.carlos.primeraapp.Adapters;

import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.carlos.primeraapp.HttpRequest.API;
import com.carlos.primeraapp.HttpRequest.ApiService;
import com.carlos.primeraapp.HttpRequest.Response.Notes;
import com.carlos.primeraapp.HttpRequest.Response.Sql;
import com.carlos.primeraapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//la clase normal la convertimos a list adapter
public class NotesAdapter implements ListAdapter {
    Notes[] notes;
    FragmentActivity activity;
    API api;
    Button btnDeleteNote;
    public NotesAdapter(Notes[] notes, FragmentActivity activity, API api) {
        this.notes = notes;
        this.activity = activity;
        this.api = api;
    }


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        //para que retorne la longuitud del array
        return notes.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        //tambien
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //retornar la vista ahora si
        if (view == null) {
            //todavia no se ha creado, tons lo generamos
            LayoutInflater layoutinflate = LayoutInflater.from(activity);
            //llamamos a view para crear una nueva vista
            view = layoutinflate.inflate(R.layout.notes_layout, null);

            //llenamos el view
            TextView title = view.findViewById(R.id.txtAdapterTitle);
            btnDeleteNote = view.findViewById(R.id.btnDeleteAdapter);
            title.setText(notes[i].getTitle());
            TextView message = view.findViewById(R.id.txtAdapterMessage);
            message.setText(notes[i].getMessage());
            btnDeleteNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(api.getURL())
                                .addConverterFactory((GsonConverterFactory.create()))
                                .build();
                        ApiService apiService = retrofit.create(ApiService.class);
                        Call<Sql> call = apiService.deleteNote(notes[i].getId());
                        call.enqueue(new Callback<Sql>() {
                            @Override
                            public void onResponse(Call<Sql> call, Response<Sql> response) {
                                if(!response.isSuccessful()){
                                    Log.d("notes", "onResponse: " + response.code());
                                    return;
                                } else{
                                    // Obtenemos la respuesta en un array
                                    Sql notes = response.body();
                                    Toast.makeText(btnDeleteNote.getContext(), "Se elimino con exito", Toast.LENGTH_SHORT).show();
                                    Log.d("notes", notes.toString());
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
            });
        }
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        //tambien
        return i;
    }

    @Override
    public int getViewTypeCount() {
        //tambien
        return notes.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
