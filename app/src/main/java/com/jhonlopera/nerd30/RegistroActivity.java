package com.jhonlopera.nerd30;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    private String correo, contraseña, repcontraseña,nombre;
    private EditText ecorreo, econtraseña, erepcontraseña,enombre;
    int duration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        ecorreo = (EditText) findViewById(R.id.eCorreo);
        econtraseña = (EditText) findViewById(R.id.eContraseña);
        erepcontraseña = (EditText) findViewById(R.id.eRepcontraseña);
        enombre = (EditText) findViewById(R.id.eNombre);

    }

    public void Registrarse(View view) {

        if (TextUtils.isEmpty((ecorreo.getText().toString()))) {
            ecorreo.setError("Llene este espacio");
        } else if (TextUtils.isEmpty((econtraseña.getText().toString()))) {
            econtraseña.setError("Llene este espacio");
        } else if (TextUtils.isEmpty((erepcontraseña.getText().toString()))) {
            erepcontraseña.setError("Llene este espacio");
        } else if (TextUtils.isEmpty((enombre.getText().toString()))) {
            enombre.setError("Llene este espacio");
        }else {
            correo = ecorreo.getText().toString();
            contraseña = econtraseña.getText().toString();
            repcontraseña = erepcontraseña.getText().toString();
            nombre = enombre.getText().toString();

            if (contraseña.equals(repcontraseña)) {
                Intent intent = new Intent();
                intent.putExtra("correo", correo);
                intent.putExtra("contraseña", contraseña);
                intent.putExtra("nombre",nombre);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Las contraseñas no coinciden";

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        }
    }
}
