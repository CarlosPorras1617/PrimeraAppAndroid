package com.carlos.primeraapp.ButtonNavigation.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carlos.primeraapp.Aunthentication.Login;
import com.carlos.primeraapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Config extends Fragment {
    //personalizar alerta
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_config, container, false);

        //ahora si, acceder a lo atributos xml una vez creado el layout de fragment_config
        Button eliminarCuenta = view.findViewById(R.id.eliminarCuentaBoton);
        eliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                //1 cambiar contrasena y 2 para eliminar la cuenta
                setAlertDialog(2);
            }
        });

        return view;
    }

    private void setAlertDialog(int option) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_authentication, null);

        EditText email = dialogView.findViewById(R.id.dialogEmail);
        EditText password = dialogView.findViewById(R.id.dialogPassword);
        Button reauthenticate = dialogView.findViewById(R.id.ReAuthenticationBoton);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        reauthenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                AuthCredential credential = EmailAuthProvider
                        .getCredential(email.getText().toString(), password.getText().toString());
                if (user != null) {
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                       getOption(option, alertDialog);
                                    }else{
                                        alertDialog.dismiss();
                                        Toast.makeText(getContext(), "Error al autenticar", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

    }

    private void getOption(int option, AlertDialog alertDialog) {
        if (option == 1) {

        }else{
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                alertDialog.dismiss();
                                Toast.makeText(getContext(), "Usuario Eliminado", Toast.LENGTH_SHORT).show();
                                //cerramos la sesion de este usuario
                                FirebaseAuth.getInstance().signOut();
                                //redirigimos a la otra pestaña
                                startActivity(new Intent(getContext(), Login.class));
                                //cerramos historial de pantallas
                                getActivity().finish();
                            }else{
                                alertDialog.dismiss();
                                Toast.makeText(getContext(), "Error al autenticar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}