package com.vedruna.finalmultimedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import dalvik.bytecode.Opcodes;


/**
 * Actividad para iniciar sesión.
 */
public class login extends AppCompatActivity {

    private TextView textView;
    private GoogleSignInClient client;

    private TextView mensaje;
    private EditText user;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Inicialización de elementos de la interfaz de usuario y configuración de Google Sign-In
        textView = findViewById(R.id.signInWithGoogle);
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this,options);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = client.getSignInIntent();
                startActivityForResult(i,1234);

            }
        });


        // Inicialización de campos de texto y botones
        mensaje = findViewById(R.id.ComprobacionLogin);
        user = findViewById(R.id.inputUser);
        password = findViewById(R.id.inputPassword);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    // Si la autenticación es exitosa, inicia la actividad principal
                                    Intent intent = new Intent(getApplicationContext(),ContenedorFragmentActivity.class);
                                    startActivity(intent);

                                }else {
                                    // Si hay un error, muestra un mensaje de error
                                    Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }

    }


    /**
     * Método para manejar el clic en el botón de inicio de sesión.
     *
     * @param view La vista del botón.
     */
    public void onClick(View view){
        String username = user.getText().toString();
        String contrasena = password.getText().toString();

        // Verifica si el nombre de usuario y la contraseña son válidos
        if (username.equals("admin") && contrasena.equals("admin")){
            String usuario = username;
            Intent intent = new Intent(login.this, ContenedorFragmentActivity.class);
            intent.putExtra("usuario",usuario);
            startActivity(intent);

        } else {
            mensaje.setText("Usuario o contraseña incorrecta");
        }

    }
}