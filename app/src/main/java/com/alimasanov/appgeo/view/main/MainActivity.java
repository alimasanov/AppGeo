package com.alimasanov.appgeo.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alimasanov.appgeo.R;
import com.alimasanov.appgeo.view.addActivity.AddActivity;
import com.alimasanov.appgeo.view.listActivity.ListActivity;

public class MainActivity extends AppCompatActivity {

    private Intent intent1;
    private Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_add = findViewById(R.id.add_butt);
        Button button_show = findViewById(R.id.show_butt);

        intent1 = new Intent(this, AddActivity.class);
        intent2 = new Intent(this, ListActivity.class);

        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });
    }
}
