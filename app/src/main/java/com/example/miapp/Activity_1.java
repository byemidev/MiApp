package com.example.miapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class Activity_1 extends AppCompatActivity {
    private Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        //sobrecarga del metodo onCreate() para trasmitir el texto de la vista MainActivity de EditText a Activity 1
        TextView textview = (TextView) findViewById(R.id.txtViewActivity_1);
        if(getIntent()!= null && getIntent().hasExtra(Intent.EXTRA_TEXT)){
            textview.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }

        btn1 = (Button) findViewById(R.id.btn_github);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/byemidev/MiApp"));
                startActivity(i1);
            }
        });

        btn2= (Button) findViewById(R.id.btn_linkedin);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/emiarevalodev/"));
                startActivity(i2);
            }
        });
        //abrir Activity_2 - descripcion de la app
        btn3 = (Button) findViewById(R.id.btn_switch_to_activity2);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToActivity2();
            }
        });
    }



    public void switchToActivity2(){
        Intent i = new Intent(this, Activity_2.class);
        startActivity(i);
    }
    public void descargarPDF() { //TODO
        // No esta funcionando se me esta cerrando la app, posiblemente por los permisos ??
        try {

            URL url = new URL("https://github.com/byemidev/getServicioMiApp/blob/8f2c102b165b8efc4bab1148a7ef2cf3154e90b4/Resume-GEMI-en-de.pdf");
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String Path = Environment.getExternalStorageDirectory() + "/download/";
            Log.v("PdfManager", "PATH: " + Path);
            File file = new File(Path);
            file.mkdirs();
            FileOutputStream fos = new FileOutputStream("tusitio.pdf");

            InputStream is = c.getInputStream();

            byte[] buffer = new byte[702];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            Log.d("PdfManager", "Error: " + e);
        }
        Log.v("PdfManager", "Check: ");
    }
}