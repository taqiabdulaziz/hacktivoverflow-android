package com.example.overflow;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String url = "http://35.247.146.48:3000/questions";
    public ArrayList<String> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject question = response.getJSONObject(i);
                        Log.i("WOI", question.optString("title"));
                        questions.add(question.optString("title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(questions, getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                Log.i("INI QUESTIONS", String.valueOf(questions));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "onErrorResponse: " + error.toString());

            }
        })  {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjNGVkMjBlNWI4YjY4MGI0NGIxMzM3OCIsImVtYWlsIjoic3Z4LmFubmV4aXZAZ21haWwuY29tIiwiaWF0IjoxNTQ4ODE0MTgzfQ.e9eDyyTwXjhOMtiL-F1a5_aU_wRJLxFiJ4BzRuQzxRQ");
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }
}
