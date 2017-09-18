package com.jhonlopera.nerd30;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    private String correoR,contraseñaR;
    int duration = Toast.LENGTH_SHORT;
    private TextView tvcorreo,tvcontraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvcorreo=(TextView) findViewById(R.id.tNombre);
        tvcontraseña=(TextView) findViewById(R.id.tContraseña);

        Bundle extras= getIntent().getExtras();
        correoR=extras.getString("correo");
        contraseñaR=extras.getString("contraseña");

        Context context = getApplicationContext();
        CharSequence text = correoR+ contraseñaR;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        tvcorreo.setText("Correo: "+correoR);
        tvcontraseña.setText("Contraseña: "+contraseñaR);


    }
}
