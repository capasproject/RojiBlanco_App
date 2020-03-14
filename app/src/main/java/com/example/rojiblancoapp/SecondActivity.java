package com.example.rojiblancoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    TextView nombre;
    TextView correo;
    TextView textID;
    Button buttonSOut;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        imageView = findViewById(R.id.imageView);
        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        textID = findViewById(R.id.textID);
        buttonSOut = findViewById(R.id.buttonSOut);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            String url = String.valueOf(personPhoto);
            Glide.with(this) .load(url) .into(imageView);
            nombre.setText(personName);
            correo.setText(personEmail);
            textID.setText(personId);
        } else {
            String url = "https://www.elheraldo.co/sites/default/files/articulo/2020/01/07/borjacamisetapuesta.jpg";
            Glide.with(this) .load(url) .into(imageView);
        }

        buttonSOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonSOut:
                        signOut();
                        break;
                }
            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SecondActivity.this,"Exito al salir",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
