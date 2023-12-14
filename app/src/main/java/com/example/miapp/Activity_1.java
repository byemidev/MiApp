package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private Button btn1, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        btn1 = (Button) findViewById(R.id.btn_github);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/byemidev/MiApp"));
                startActivity(i1);
            }
        });

        btn2= (Button) findViewById(R.id.btn_linkedin);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/emiarevalodev/"));
                startActivity(i2);
            }
        });
    }

    public void descargarPDF(){
        String cv = "CV.pdf";
        URL url = null;
        try {
            //Inicio Conexion
            url = new URL("urlgithub/" + cv); //TODO
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();
            //
            String Path = Environment.getExternalStorageDirectory() + "/download/";
            Log.v("PdfManager", "PATH: " + Path);
            File pdf = new File(Path);
            //
            pdf.mkdir();
            if(pdf.exists()){
                try{
                    FileOutputStream fos = new FileOutputStream(pdf);
                    InputStream is = connection.getInputStream();
                    //TODO
                    Byte [] buffer = new Byte[1024];
                    // While()...
                    //Closing..
                }catch (FileNotFoundException e){
                    System.err.printf( "Error en descargarPDF--> \n %d", e.getCause());
                }
            }
            connection.disconnect();//Nos podemos desconectar una vez se ha realizado la conexion
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}