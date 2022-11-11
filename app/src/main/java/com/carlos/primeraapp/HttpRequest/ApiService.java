package com.carlos.primeraapp.HttpRequest;

import com.carlos.primeraapp.HttpRequest.Request.NoteSchema;
import com.carlos.primeraapp.HttpRequest.Response.Notes;
import com.carlos.primeraapp.HttpRequest.Response.Sql;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

//funciona como las routes
public interface ApiService {
    @GET("notes/")
    Call<Notes[]> getNotes();

    @DELETE("notes/{id}/")
    //identificar con el pathe como se llamara el parametro
    //call sql poruqe asi se llama la clase sql.java
    Call<Sql> deleteNote(@Path("id") int id);

    //peticion post
    @POST("notes/")
    //recibe un cuerpo de peticion
    Call<Sql> addNote(@Body NoteSchema noteSchema);

}
