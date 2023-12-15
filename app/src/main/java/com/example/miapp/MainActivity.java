package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button btn_entrar;
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mail, pass, btn_entrar
        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        btn_entrar = (Button) findViewById(R.id.button_entrar);

        //onClickListener()
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_aux = email.getText().toString().toLowerCase().trim();
                String pass_aux = pass.getText().toString().toLowerCase().trim();
                if(!validarEmail(email_aux)){//validar email
                    email.setError("email no valido");
                }else{//si el email es valido..
                    if(!validarContrasena(pass_aux)){//validar pass
                        pass.setError("contraseña invalida");
                        btn_entrar.setBackgroundColor(Color.RED);
                    }else{
                        btn_entrar.setBackgroundColor(Color.GREEN);
                        switchToActivity_1();
                    }
                }
            }
        });
    }
    //validar email
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    //validar contraseña
    private boolean validarContrasena(String contrasena) {
        return contrasena != null && contrasena.length() >= 6;
    }
    //cambiar a Activity_1
    public void switchToActivity_1(){
        Intent i = new Intent(this, Activity_1.class);
        startActivity(i);
    }

    //Devolver mi correo para que salga en Activity_1 como TextView en Top de mi layout
    public static final String REQUEST_RESULT= "REQUEST_RESULT";
    public void switch1(View view){
        EditText email = findViewById(R.id.editTextTextEmailAddress);

        String text = email.getText().toString();

        Intent i = new Intent(this, Activity_1.class);
        i.putExtra(Intent.EXTRA_TEXT, text);
        startActivityForResult(i, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Toast.makeText(this, Integer.toString(data.getIntExtra  (REQUEST_RESULT, 0)), Toast.LENGTH_LONG).show();
        }
    }
}