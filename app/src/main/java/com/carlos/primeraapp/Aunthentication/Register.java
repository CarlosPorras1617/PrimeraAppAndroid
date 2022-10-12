package com.carlos.primeraapp.Aunthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carlos.primeraapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    //declaramos botones
    Button botonIniciarSesion, botonRegistrarseToApp;
    EditText txtNombreToRegister, txtCorreoToRegister, txtPasswordToRegister;
    //firebase
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //instanciamos firebase
        mAuth = FirebaseAuth.getInstance();
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

        if (password.matches("(.*)[A-Z](.*)") || password.matches("(.*)[@#$%^&+=](.*)")) {
            //Toast.makeText(this, "Contraseña Aceptada", Toast.LENGTH_SHORT).show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                /*Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();*/
                                updateUI(null);
                            }
                        }//METODO PARA SABER SI ESTA OCURRIENDO UN ERROR
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(this, "La contraseña debe contener una mayuscula o un simbolo", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateUI(FirebaseUser user){
        if (user != null) {
            Toast.makeText(this, "Registro Exitoso. ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Error al registrar. ", Toast.LENGTH_SHORT).show();
        }
    }

    public void metodoCambiarVentana2(){
        //instancia declaramos la clase donde estamos, despues indicamos a que clase ira la siguiente actividad
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }
}