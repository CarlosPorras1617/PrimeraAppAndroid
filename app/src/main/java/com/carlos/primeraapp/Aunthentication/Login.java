package com.carlos.primeraapp.Aunthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carlos.primeraapp.ButtonNavigation.Home;
import com.carlos.primeraapp.R;
import com.carlos.primeraapp.intent.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    //declaramos botones e input
    Button botonRegistrarse, botonIniciarSesionLogin, ForgotPasswordButton;
    EditText txtEmailLogin, txtPasswordLogin;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //llamamos a los botones
        mAuth = FirebaseAuth.getInstance();
        ForgotPasswordButton = findViewById(R.id.ForgotPasswordButton);
        botonRegistrarse = findViewById(R.id.botonRegistrarse);
        botonIniciarSesionLogin = findViewById(R.id.botonIniciarSesionLogin);
        txtEmailLogin = findViewById(R.id.txtEmailLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);

        botonIniciarSesionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginToApp();
            }
        });

        //ir a olvide mi password
        ForgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotMyPasswordPage();
            }
        });

        //llamamos al evento click del boton para mandar a llamar el metodo de cambiar ventana
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoCambiarVentana();
            }
        });
    }

    private void forgotMyPasswordPage() {
        Intent intent = new Intent(Login.this, OlvidePassword.class);
        startActivity(intent);
        finish();
    }

    private void loginToApp() {
        String email = txtEmailLogin.getText().toString();
        String password = txtPasswordLogin.getText().toString();
        if (password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Estan vacios", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(Login.this, "Se inicio sesion", Toast.LENGTH_SHORT).show();
                                moveToHome();
                            } else {
                                Toast.makeText(Login.this, "Error de inicio de sesion", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }

    }

    private void moveToHome() {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void metodoCambiarVentana(){
        //instancia declaramos la clase donde estamos, despues indicamos a que clase ira la siguiente actividad
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();
    }

    //chechar estado de la sesion
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            moveToHome();
        }
    }
}