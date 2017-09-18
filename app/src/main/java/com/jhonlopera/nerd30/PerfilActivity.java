package com.jhonlopera.nerd30;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    private String correoR,contraseñaR;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Bundle extras= getIntent().getExtras();
        correoR=extras.getString("correo");
        contraseñaR=extras.getString("contraseña");

        Context context = getApplicationContext();
        CharSequence text = correoR+ contraseñaR;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }
}
