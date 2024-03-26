package com.tuan04.lab5api;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class VolleyFn2 {
    String strJSON = "";

    public void getAllDataFromAPI(Context context, TextView textView) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject person = response.getJSONObject(i);
                        int id = person.getInt("id");
                        String name = person.getString("name");
                        String username = person.getString("username");
                        strJSON += "id: " + id + "\n";
                        strJSON += "name: " + name + "\n";
                        strJSON += "username: " + username + "\n";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                textView.setText(strJSON);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
