package com.carlos.primeraapp.Aunthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.carlos.primeraapp.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //declaramos botones
        Button botonRegistrarse;

        //llamamos a los botones
        botonRegistrarse = findViewById(R.id.botonRegistrarse);

        //llamamos al evento click del boton para mandar a llamar el metodo de cambiar ventana
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoCambiarVentana();
            }
        });
    }

    public void metodoCambiarVentana(){
        //instancia declaramos la clase donde estamos, despues indicamos a que clase ira la siguiente actividad
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();
    }
}