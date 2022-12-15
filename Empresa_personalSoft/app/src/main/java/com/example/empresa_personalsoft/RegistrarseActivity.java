package com.example.empresa_personalsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrarseActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText jetusuario,jetcorreo,jetnombre,jetclave;
    CheckBox jcbactivo;
    RequestQueue rq;
    JsonRequest jrq;
    String usuario,correo,nombre,clave,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        //Asociar objetos Java y Xml
        getSupportActionBar().hide();
        jetclave=findViewById(R.id.etclave);
        jetcorreo=findViewById(R.id.etcorreo);
        jetnombre=findViewById(R.id.etnombre);
        jetusuario=findViewById(R.id.etusuario);
        jcbactivo=findViewById(R.id.cbactivo);
        rq = Volley.newRequestQueue(this);//Inicializar cola de impresion
    }

    public void Consultar(View view){
         usuario=jetusuario.getText().toString();
         if (usuario.isEmpty()){
            Toast.makeText(this, "Usuario requerido", Toast.LENGTH_SHORT).show();
            jetusuario.requestFocus();
         }else{
            url = "http://172.16.58.85/WebServices/consulta.php?usr=" + usuario;
            jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            rq.add(jrq);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
        jetusuario.requestFocus();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);//posicion 0 del arreglo....
            jetnombre.setText(jsonObject.optString("nombre"));
            jetcorreo.setText(jsonObject.optString("correo"));
            jetclave.setText(jsonObject.optString("clave"));
            if (jsonObject.optString("activo").equals("si"))
                jcbactivo.setChecked(true);
            else
                jcbactivo.setChecked(false);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void Adicionar(View view){
        usuario=jetusuario.getText().toString();
        nombre=jetnombre.getText().toString();
        correo=jetcorreo.getText().toString();
        clave=jetclave.getText().toString();
        if (usuario.isEmpty() || nombre.isEmpty() || correo.isEmpty() || clave.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetusuario.requestFocus();
        } else{
                url = "http://172.16.58.85/WebServices/registrocorreo.php";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                {
                        @Override
                        public void onResponse(String response) {
                            Limpiar_campos();
                            Toast.makeText(getApplicationContext(), "Registro de usuario realizado!", Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Registro de usuario incorrecto!", Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("usr",jetusuario.getText().toString().trim());
                    params.put("nombre", jetnombre.getText().toString().trim());
                    params.put("correo",jetcorreo.getText().toString().trim());
                    params.put("clave",jetclave.getText().toString().trim());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(postRequest);
        }
    }

    public void Modificar(View view){
        usuario=jetusuario.getText().toString();
        nombre=jetnombre.getText().toString();
        correo=jetcorreo.getText().toString();
        clave=jetclave.getText().toString();
        if (usuario.isEmpty() || nombre.isEmpty() || correo.isEmpty() || clave.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetusuario.requestFocus();
        } else{
            url = "http://http://172.16.58.85/WebServices/actualiza.php";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {
                    Limpiar_campos();
                    Toast.makeText(getApplicationContext(), "Registro de usuario realizado!", Toast.LENGTH_LONG).show();
                }
            },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Registro de usuario incorrecto!", Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("usr",jetusuario.getText().toString().trim());
                    params.put("nombre", jetnombre.getText().toString().trim());
                    params.put("correo",jetcorreo.getText().toString().trim());
                    params.put("clave",jetclave.getText().toString().trim());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(postRequest);
        }
    }

    public void Anular(View view){
        usuario=jetusuario.getText().toString();
        if (usuario.isEmpty()){
            Toast.makeText(this, "El usuario es requerido", Toast.LENGTH_SHORT).show();
            jetusuario.requestFocus();
        } else{
            url = "http://172.16.58.85//WebServices/anula.php";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {
                    Limpiar_campos();
                    Toast.makeText(getApplicationContext(), "Registro anulado!", Toast.LENGTH_LONG).show();
                }
            },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error anulando registro!", Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("usr",jetusuario.getText().toString().trim());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(postRequest);
        }
    }

    public void Limpiar(View view){
        Limpiar_campos();
    }

    public void Regresar(View view){
        Intent intmain=new Intent(this,MainActivity.class);
        startActivity(intmain);
    }

    private void Limpiar_campos(){
        jetusuario.setText("");
        jetnombre.setText("");
        jetcorreo.setText("");
        jetclave.setText("");
        jetusuario.requestFocus();
    }

}