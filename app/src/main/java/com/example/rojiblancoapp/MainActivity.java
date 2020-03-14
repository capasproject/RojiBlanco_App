package com.example.rojiblancoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    EditText textEmail;
    EditText textContraseña;
    Button buttonIniciar;
    Button buttonRegistrar;
    SignInButton buttonGoogle;
    CheckBox chMostrar;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textContraseña = findViewById(R.id.textContraseña);
        textEmail= findViewById(R.id.textEmail);
        chMostrar = findViewById(R.id.chMostrar);
        buttonIniciar = findViewById(R.id.buttonIniciar);
        buttonRegistrar = findViewById(R.id.buttonRegistrarse);
        buttonGoogle = findViewById(R.id.sign_in_button);

        chMostrar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textContraseña.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    textContraseña.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar(v);
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.v("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void iniciar(View v) {
        String pass = textContraseña.getText().toString();
        String user = textEmail.getText().toString();

        if (user.length() == 0){
            Toast.makeText(this,"Ingrese su email",Toast.LENGTH_SHORT).show();
        }
        if (pass.length() == 0){
            Toast.makeText(this,"Ingrese su contraseña",Toast.LENGTH_SHORT).show();
        }
        if (user.length() != 0 && pass.length() != 0){
            if (textContraseña.getText().toString().equals("admin") && textEmail.getText().toString().equals("admin")) {
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this,"Error: Datos incorrectos",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
