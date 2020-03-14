package com.example.rojiblancoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    Button buttonRRegistrar;
    Button buttonRRegresar;
    TextView nombre;
    TextView email;
    TextView contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        nombre = findViewById(R.id.textRNombre);
        email = findViewById(R.id.textREmail);
        contraseña = findViewById(R.id.textRContra);
        buttonRRegresar = findViewById(R.id.buttonRRegresar);
        buttonRRegistrar = findViewById(R.id.buttonRRegistrar);
        buttonRRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar(v);
            }
        });
        buttonRRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar(v);
            }
        });
    }

    private void regresar (View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void validar (View v){
        if (nombre.getText().toString().length() == 0){
            Toast.makeText(this,"Ingrese su nombre",Toast.LENGTH_LONG).show();
        } else {
            if (email.getText().toString().length() == 0){
                Toast.makeText(this,"Ingrese su email",Toast.LENGTH_LONG).show();
            } else {
                if (contraseña.getText().toString().length() == 0){
                    Toast.makeText(this,"Ingrese su contraseña",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,"Exito al Registrar",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
            }
        }
    }
}
