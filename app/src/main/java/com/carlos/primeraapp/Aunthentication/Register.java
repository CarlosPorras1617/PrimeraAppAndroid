package com.carlos.primeraapp.Aunthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.carlos.primeraapp.R;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //declaramos botones
        Button botonIniciarSesion;

        //llamamos a los botones
        botonIniciarSesion = findViewById(R.id.botonIniciarSesion);

        //llamamos al evento click
        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoCambiarVentana2();
            }
        });
    }

    public void metodoCambiarVentana2(){
        //instancia declaramos la clase donde estamos, despues indicamos a que clase ira la siguiente actividad
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }
}