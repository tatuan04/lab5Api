package com.tuan04.lab5api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView tvKq2;
    Button btnGetData2;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnGetData2 = findViewById(R.id.btnGetData2);
        tvKq2 = findViewById(R.id.txtKq2);

        btnGetData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyFn2 volleyFn2 = new VolleyFn2();
                volleyFn2.getAllDataFromAPI(context, tvKq2);
            }
        });
    }
}