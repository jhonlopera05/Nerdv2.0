package com.jhonlopera.nerd30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private String correoR,contraseñaR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void iniciar(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("correo",correoR);
        intent.putExtra("contraseña",contraseñaR);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this,"hola",Toast.LENGTH_SHORT);
        if (requestCode==1234 && resultCode==RESULT_OK){
            correoR=data.getExtras().getString("correo");
            contraseñaR=data.getExtras().getString("contraseña");
            Toast.makeText(this,correoR,Toast.LENGTH_SHORT);
            Log.d("correo",correoR);
            Log.d("pass",contraseñaR);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Registrarse(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivityForResult(intent,1234);
    }


}
