package com.tuan04.lab5api

import android.content.Context
import android.widget.TextView
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class VolleyFn1 {
    var strJSON = ""
    // ham doc du lieu tu api
    fun getAllData(context: Context, textview: TextView) {
        //1. tao request
        val requestQueue = Volley.newRequestQueue(context)
        //2. truyen url
        val url = "https://jsonplaceholder.typicode.com/users"
        //3. goi request
        //mang cua cac doi tuong -> goi mang truoc, doi tuong sau
        //JsonArrayRequest(url, thanhcong, thaibai)
        val request = JsonArrayRequest(url, { response ->
                  for (i in 0 until response.length()) {
                      try {
                          val person = response.getJSONObject(i)
                          val id = person.getInt("id")
                          val name = person.getString("name")
                          val username = person.getString("username")
                          //dua tat ca vao chuoi
                          strJSON += "id: $id\n"
                          strJSON += "name: $name\n"
                          strJSON += "username: $username\n"
                      }catch (e: Exception){
                          e.printStackTrace()
                      }
                  }
                  textview.text = strJSON //hien thi ket qua len man hinh
        }, { error -> textview.text = error.message })
        //4.thuc hien request
        requestQueue.add(request)
    }
}