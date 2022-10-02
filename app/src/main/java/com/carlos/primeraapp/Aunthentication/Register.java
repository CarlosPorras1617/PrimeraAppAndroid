package com.carlos.primeraapp.Aunthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carlos.primeraapp.R;

public class Register extends AppCompatActivity {
    //declaramos botones
    Button botonIniciarSesion, botonRegistrarseToApp;
    EditText txtNombreToRegister, txtCorreoToRegister, txtPasswordToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //llamamos a los botones
        botonIniciarSesion = findViewById(R.id.botonIniciarSesion);
        botonRegistrarseToApp = findViewById(R.id.botonRegistrarseToApp);
        txtNombreToRegister = findViewById(R.id.txtNombreToRegister);
        txtCorreoToRegister = findViewById(R.id.txtCorreoToRegister);
        txtPasswordToRegister = findViewById(R.id.txtPasswordToRegister);

        botonRegistrarseToApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpToApp();
            }
        });


        //llamamos al evento click
        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoCambiarVentana2();
            }
        });
    }

    private void signUpToApp() {
        String email = txtCorreoToRegister.getText().toString();
        String password = txtPasswordToRegister.getText().toString();
        Toast.makeText(this, "Los datos son "+email+" "+password, Toast.LENGTH_SHORT).show();
    }

    public void metodoCambiarVentana2(){
        //instancia declaramos la clase donde estamos, despues indicamos a que clase ira la siguiente actividad
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }
}