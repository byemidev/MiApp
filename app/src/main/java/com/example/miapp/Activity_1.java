package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/byemidev"));
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
}