package com.jhonlopera.nerd30;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private String correoR,contraseñaR, correo, contraseña;
    private EditText ecorreo, econtraseña;
    int duration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ecorreo = (EditText) findViewById(R.id.eCorreo);
        econtraseña = (EditText) findViewById(R.id.eContraseña);

        Bundle extras= getIntent().getExtras();

        if (extras != null){
            correoR=extras.getString("correo");
            contraseñaR =extras.getString("contraseña");

            Context context = getApplicationContext();
            CharSequence text = correoR+ contraseñaR;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void iniciar(View view) {

        correo = ecorreo.getText().toString();
        contraseña = econtraseña.getText().toString();

        if(correo.equals(correoR) && contraseña.equals(contraseñaR)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("correo",correoR);
            intent.putExtra("contraseña",contraseñaR);
            startActivity(intent);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "Datos incorrectos";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this,"hola",Toast.LENGTH_SHORT);
        if (requestCode==1234 && resultCode==RESULT_OK){
            correoR=data.getExtras().getString("correo");
            contraseñaR=data.getExtras().getString("contraseña");
            Toast.makeText(this,correoR,Toast.LENGTH_SHORT);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Registrarse(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivityForResult(intent,1234);
    }

}
