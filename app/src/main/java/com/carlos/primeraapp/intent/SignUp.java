package com.carlos.primeraapp.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.carlos.primeraapp.R;

public class SignUp extends AppCompatActivity {
    //se declaran aqui porque son globales
    EditText txtNombre, txtEdad, txtMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button explicito, implicito;

        //mando a llamar al boton
        explicito = findViewById(R.id.botonExplicito);
        implicito = findViewById(R.id.botonImplicito);
        //obtenemos valores de los input
        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        txtMensaje= findViewById(R.id.txtMensaje);

        explicito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                metodoExplicito();
            }
        });

        implicito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoImplicito();
            }
        });
        //ya se pueden utilizar sus propiedades
        Log.d("cicloDeVida", "onCreate()");
    }
    //metodo
    private void metodoImplicito(){
        //obtenemos el valor del mensaje
        String mensaje = txtMensaje.getText().toString();
        //instanciamos el intent
        Intent intent = new Intent();
        //anadimos la accion que haremos con el, en este caso, enviar
        intent.setAction(Intent.ACTION_SEND);
        //Mandamos el texto
        intent.putExtra(Intent.EXTRA_TEXT, mensaje);
        //lo mandamos como texto plano
        intent.setType("text/plain");
        //iniciamos la actividad
        startActivity(intent);
    }

    //metodo
    private void metodoExplicito(){
        //instancia declaramos la clase donde estamos, despues indicamos a que clase ira la siguiente actividad
        //Intent intent = new Intent(SignUp.this, MainActivity.class);
        //enviaremos el intnetn que creamos para crear una nueva pantalla y desstruira la pantalla anterior
        //enviar los datos
        //intent.putExtra("nombreDeUsuario", txtNombre.getText().toString());
        //intent.putExtra("edadUsuario", Integer.parseInt(txtEdad.getText().toString()));
        //startActivity(intent);
        //para destruir la pantalla anterior
        //finish();

        //Intent Explicito mediante paquete
        //obetenemos el mensaje
        String mensaje = txtMensaje.getText().toString();
        //vinvulamos la uri para hacer la busqueda en google maps
        Uri uri = Uri.parse("geo:0,0?q=" + mensaje);
        //intanciamos
        Intent intent2 = new Intent();
        //Decimos que la accion sera view
        intent2.setAction(Intent.ACTION_VIEW);
        //Le indicamos que uri se mandara al servicio de busqueda de maps
        intent2.setData(uri);
        //url de google maps
        intent2.setPackage("com.google.android.apps.maps");
        startActivity(intent2);
    }
    }
