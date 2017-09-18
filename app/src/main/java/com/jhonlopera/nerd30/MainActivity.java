package com.jhonlopera.nerd30;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  String CorreoR,contraseñaR;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Lo que se envia siempre s eextrae en el metodo oncreate

        Bundle extras= getIntent().getExtras();
        CorreoR=extras.getString("correo");
        contraseñaR=extras.getString("contraseña");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        Intent intent;

        switch (id){
            case R.id.mPerfil:

                /*Context context = getApplicationContext();
                CharSequence text = Correop + contraseñap;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/

                intent = new Intent(this, PerfilActivity.class);
                intent.putExtra("correo",CorreoR);
                intent.putExtra("contraseña",contraseñaR);
                startActivity(intent);

                break;
            case R.id.mCerrar:
                intent=new Intent(this,LoginActivity.class);
                intent.putExtra("correo",CorreoR);
                intent.putExtra("contraseña",contraseñaR);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}