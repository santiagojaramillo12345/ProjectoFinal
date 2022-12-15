package com.example.empresa_personalsoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ocultar la barra de menu por defecto
        getSupportActionBar().hide();
        //Libreria para el manejo de vistas (fragment)
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.escenario,new IngresoFragment()).commit();
    }
}