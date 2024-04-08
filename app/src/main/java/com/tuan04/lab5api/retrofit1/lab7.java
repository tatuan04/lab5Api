package com.tuan04.lab5api.retrofit1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tuan04.lab5api.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class lab7 extends AppCompatActivity {
    EditText txtPid, txtName,txtPrice,txtDescription;
    TextView txtKetQua;
    Button btnInsert, btnUpdate, btnDelete, btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab7);

        txtPid = findViewById(R.id.txtPid);
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
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRetrofit();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRetrofit();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRetrofit();
            }
        });
    }

    ArrayList<Prd> ls;
    String strKQ="";//chuoi chua ket qua
    private void selectRetrofit() {
        //B0. Chuan bi du lieu
        //b1. Tao doi tuong Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.1.17/lab6/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterSelect interSelect=retrofit.create(InterSelect.class);
        Call<SvResponseSelect> call=interSelect.selectPrd();
        call.enqueue(new Callback<SvResponseSelect>() {
            @Override
            public void onResponse(Call<SvResponseSelect> call, Response<SvResponseSelect> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SvResponseSelect svrResponseSelect = response.body();
                    Prd[] products = svrResponseSelect.getProducts();
                    if (products != null) {
                        ls = new ArrayList<>(Arrays.asList(products));
                        strKQ = "";
                        for (Prd p : ls) {
                            strKQ += "Pid: " + p.getPid() + " - Name: " + p.getName() + " - Price: " + p.getPrice() + " - Description: " + p.getDesciption() + "\n";
                        }
                        txtKetQua.setText(strKQ);
                    } else {
                        txtKetQua.setText("No products found in response");
                    }
                } else {
                    txtKetQua.setText("Response is not successful or null");
                }
            }

            @Override
            public void onFailure(Call<SvResponseSelect> call, Throwable t) {
                txtKetQua.setText(t.getMessage());
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
                .baseUrl("http://192.168.1.17/lab6/")
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
    private void deleteRetrofit() {
        //B0. Chuan bi du lieu
        Prd prd =new Prd();
        prd.setPid(txtPid.getText().toString());
        //b1. Tao doi tuong Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.1.17/lab6/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterDelete interDelete=retrofit.create(InterDelete.class);
        Call<SvResponseDelete> call=interDelete.deletePrd(prd.getPid());
        call.enqueue(new Callback<SvResponseDelete>() {
            @Override
            public void onResponse(Call<SvResponseDelete> call, Response<SvResponseDelete> response) {
                SvResponseDelete svResponseDelete=response.body();
                txtKetQua.setText(svResponseDelete.getMessage());
            }

            @Override
            public void onFailure(Call<SvResponseDelete> call, Throwable t) {
                txtKetQua.setText(t.getMessage());
            }
        });
    }

    private void updateRetrofit() {
        //B0. Chuan bi du lieu
        Prd p=new Prd();
        p.setPid(txtPid.getText().toString());
        p.setName(txtName.getText().toString());
        p.setPrice(txtPrice.getText().toString());
        p.setDesciption(txtDescription.getText().toString());
        //b1. Tao doi tuong Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.1.17/lab6/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterUpdate interUpdate=retrofit.create(InterUpdate.class);
        Call<SvResponseUpdate> call=interUpdate.updatePrd(p.getPid(),
                p.getName(),p.getPrice(),p.getDesciption());
        call.enqueue(new Callback<SvResponseUpdate>() {
            @Override
            public void onResponse(Call<SvResponseUpdate> call, Response<SvResponseUpdate> response) {
                SvResponseUpdate svResponseUpdate=response.body();
                txtKetQua.setText(svResponseUpdate.getMessage());
            }

            @Override
            public void onFailure(Call<SvResponseUpdate> call, Throwable t) {
                txtKetQua.setText(t.getMessage());
            }
        });
    }
}