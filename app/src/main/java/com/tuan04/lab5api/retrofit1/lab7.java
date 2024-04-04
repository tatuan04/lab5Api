package com.tuan04.lab5api.retrofit1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tuan04.lab5api.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class lab7 extends AppCompatActivity {
    EditText txtName,txtPrice,txtDescription;
    TextView txtKetQua;
    Button btnInsert, btnUpdate, btnDelete, btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab7);

        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
        txtKetQua = findViewById(R.id.txtKetQua);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect = findViewById(R.id.btnSelect);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRetrofit();
            }
        });
    }

    private void insertRetrofit() {
        //1. tao doi tuowng chua du lieu
        Prd prd = new Prd();
        //2. dua du lieu vao doi tuong
        prd.setName(txtName.getText().toString());
        prd.setPrice(txtPrice.getText().toString());
        prd.setDesciption(txtDescription.getText().toString());
        //3. tao doi tuong retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.15/lab6/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        //4. goi ham ínert trong interface
        //4.1 tao doi tuong
        InterInsertPrd insertPrdObj = retrofit.create(InterInsertPrd.class);
        //4.2 chuan bi ham
        Call<SvResponsePrd> call = insertPrdObj.insertPrd(prd.getName(), prd.getPrice(),prd.getDesciption());
        call.enqueue(new Callback<SvResponsePrd>() {
            @Override
            public void onResponse(Call<SvResponsePrd> call, Response<SvResponsePrd> response) {
                SvResponsePrd svResponsePrd = response.body();//lay ket qua ma sever tra ve
                txtKetQua.setText(svResponsePrd.getMessage());
            }
            //thai bai
            @Override
            public void onFailure(Call<SvResponsePrd> call, Throwable t) {
                txtKetQua.setText(t.getMessage());
            }
        });
    }
}