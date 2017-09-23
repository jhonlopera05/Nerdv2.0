package com.jhonlopera.nerd30;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    private String correoR,contraseñaR,nombreR;
    int duration = Toast.LENGTH_SHORT;
    private TextView tvcorreo,tvnombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvcorreo=(TextView) findViewById(R.id.tNombre);
        tvnombre=(TextView) findViewById(R.id.tvCorreo);


        Bundle extras= getIntent().getExtras();
        correoR=extras.getString("correo");
        contraseñaR=extras.getString("contraseña");

        Context context = getApplicationContext();
        CharSequence text = correoR+ contraseñaR;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        tvcorreo.setText("Correo: "+correoR);
        tvnombre.setText("Contraseña: "+nombreR);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuperfil,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        Intent intent;

        switch (id){

            case R.id.mPrincipal:
                intent = new Intent(PerfilActivity.this, MainActivity.class);
                intent.putExtra("nombre: ",nombreR);
                intent.putExtra("contraseña: ",contraseñaR);
                intent.putExtra("correo",correoR);
                startActivity(intent);
                break;

            case R.id.mCerrar:
                intent=new Intent(PerfilActivity.this,LoginActivity.class);
                intent.putExtra("correo",correoR);
                intent.putExtra("contraseña",contraseñaR);
                intent.putExtra("nombre: ",nombreR);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


        }

        return super.onOptionsItemSelected(item);

    }
}
