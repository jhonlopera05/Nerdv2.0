package com.jhonlopera.nerd30;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    private  String correoR,contraseñaR,nombreR;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Lo que se envia siempre s eextrae en el metodo oncreate

        Bundle extras= getIntent().getExtras();
        if (extras != null){
            correoR=extras.getString("correo");
            contraseñaR =extras.getString("contraseña") ;
            nombreR=extras.getString("nombre");

            Context context = getApplicationContext();
            CharSequence text = correoR+ nombreR+contraseñaR;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

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

                intent = new Intent(this, PerfilActivity.class);
                intent.putExtra("correo",correoR);
                intent.putExtra("nombre",nombreR);
                startActivity(intent);

                break;
            case R.id.mCerrar:
                Context context = getApplicationContext();
                CharSequence text = correoR+ contraseñaR+nombreR;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                LoginManager.getInstance().logOut();
                intent=new Intent(this,LoginActivity.class);
                intent.putExtra("correo",correoR);
                intent.putExtra("contraseña",contraseñaR);
                intent.putExtra("nombre",nombreR);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}