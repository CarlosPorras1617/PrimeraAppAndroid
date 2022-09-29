package com.carlos.primeraapp.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.carlos.primeraapp.R;


public class MainActivity extends AppCompatActivity {
    // inicializar componentes nombre no igual al id de los botones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtener valores
        Intent mensaje = getIntent();
        String nombre = mensaje.getStringExtra("nombreDeUsuario");
        int edad = mensaje.getIntExtra("edadUsuario", 0);
            if (edad >= 18) {
                Toast.makeText(this, String.valueOf(nombre + " mayor de edad"), Toast.LENGTH_SHORT).show();
            } else if (edad < 18 && edad > 0) {
                Toast.makeText(this, String.valueOf(nombre + " menor de edad"), Toast.LENGTH_SHORT).show();
            }




        //declaramos el boton
        Button regresar;
        //le asignamos valor
        regresar = findViewById(R.id.botonRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoRegresar();
            }
        });
    }


    private void metodoRegresar(){
        //declaramos el origen y el destino
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        //enviamos el intent que creamos para volver a la pantalla anterior y destruir la actual
        startActivity(intent);
        finish();
    }
}

