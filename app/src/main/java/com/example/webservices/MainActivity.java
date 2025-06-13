package com.example.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText e1,e2;
Button b;
TextView resultadoOro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    relacionarVistas();
    }

    public void relacionarVistas(){
        e1=(EditText) findViewById(R.id.usuario);
        e2=(EditText) findViewById(R.id.password);
        b=(Button) findViewById(R.id.b);
        resultadoOro=(TextView) findViewById(R.id.oro);
    }
    public void webServices(View c){
        String u=e1.getText().toString();
        String p=e2.getText().toString();
        RequestQueue servicio= Volley.newRequestQueue(this);
String url="http://172.16.1.12/services/login.php?u="+u+"&p="+p;
        StringRequest respuesta= new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    mensaje(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      mensaje(error.toString());
                    }
                });
        servicio.add(respuesta);
    }
    public void mensaje(String texto){
        Toast.makeText(getApplicationContext(),texto,Toast.LENGTH_LONG).show();
    }
    public void webServices2(View x){
    RequestQueue servicio2= Volley.newRequestQueue(this);
        String u=e1.getText().toString();
        String p=e2.getText().toString();
        String url="http://10.29.179.188/backend_4951/index.php";
        StringRequest respuesta=new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mensaje(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mensaje(error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> mapa= new HashMap<>();
                mapa.put("u",u);
                mapa.put("p",p);
                return mapa;
            }
        };
        servicio2.add(respuesta);
    }
    public void webServices3(View v)T{
        resultadoOro.setText("");
        RequestQueue servicio= Volley.newRequestQueue(this);
        String url="http://172.16.1.12/services/seleccionar.php";
        JsonArrayRequest respuesta= new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                    JSONObject j=null;
                        ArrayList lista=new ArrayList();
                        for(int i=0;i<response.length();i++) {
                            try {
                                j = response.getJSONObject(i);
                                lista.add("longitud "+j.getString("lon"));
                                lista.add("latitud "+j.getString("lat"));
                                lista.add("dirección: "+j.getString("dire")+"\n");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        resultadoOro.append(lista.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
mensaje("chk");
            }
        }
        );
        servicio.add(respuesta);
    }


}