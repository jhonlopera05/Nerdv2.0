package com.jhonlopera.nerd30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {

    private String correoperfil,contraseñaperfil;
    private TextView tNombre,tContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tNombre=(TextView) findViewById(R.id.tNombre);
        tContraseña=(TextView) findViewById(R.id.tContraseña);

        Bundle extras1=getIntent().getExtras();
        correoperfil=extras1.getString("mail");
        correoperfil=extras1.getString("pass");

        tNombre.setText("Su correo es:"+contraseñaperfil+"\n");
        tNombre.setTextColor(getResources().getColor(R.color.black));

        tContraseña.setText("Su contraseña es: "+ contraseñaperfil);
        tContraseña.setTextColor(getResources().getColor(R.color.black));
    }
}
