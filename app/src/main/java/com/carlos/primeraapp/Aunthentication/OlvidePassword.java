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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class OlvidePassword extends AppCompatActivity {
    //botones y texfields
    Button RestablecerPasswordButton, ForgotPasswordHomeButton;
    EditText txtRestablecerPassword;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide_password);

        //llamamos
        mAuth = FirebaseAuth.getInstance();
        RestablecerPasswordButton = findViewById(R.id.RestablecerPasswordButton);
        ForgotPasswordHomeButton = findViewById(R.id.ForgotPasswordHomeButton);
        txtRestablecerPassword = findViewById(R.id.txtRestablecerPassword);

        RestablecerPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restablecerPassword();
            }
        });

        ForgotPasswordHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToLogin();
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(OlvidePassword.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void restablecerPassword() {
        String email = txtRestablecerPassword.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "Correo no valido", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(OlvidePassword.this, "Se envio un correo electronico", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}