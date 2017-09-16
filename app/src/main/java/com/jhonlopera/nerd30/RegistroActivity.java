package com.jhonlopera.nerd30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity {

    private String correo,contraseña,repcontraseña;
    private EditText ecorreo,econtraseña,erepcontraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ecorreo =(EditText) findViewById(R.id.eCorreo);
        econtraseña =(EditText) findViewById(R.id.eContraseña);
        erepcontraseña =(EditText) findViewById(R.id.eRepcontraseña);
    }

    public void Registrarse(View view) {
        correo=ecorreo.getText().toString();
        contraseña=econtraseña.getText().toString();
        repcontraseña=erepcontraseña.getText().toString();

        Intent intent= new Intent();
        intent.putExtra("correo",correo);
        intent.putExtra("contraseña",contraseña);
        setResult(RESULT_OK,intent);
        finish();
    }
}
