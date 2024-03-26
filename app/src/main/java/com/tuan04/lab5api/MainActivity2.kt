package com.tuan04.lab5api

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    var context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnGetData = findViewById<Button>(R.id.btnGetData1)
        var txtKq = findViewById<TextView>(R.id.tvKq1)
        val fn1 = VolleyFn1()
        btnGetData.setOnClickListener {
            fn1.getAllData(context, txtKq!!)
        }
    }
}