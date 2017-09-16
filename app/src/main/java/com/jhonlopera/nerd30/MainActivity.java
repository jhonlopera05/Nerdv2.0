package com.jhonlopera.nerd30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  String Correop,contraseñap;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView) findViewById(R.id.tv);

        // Lo que se envia siempre s eextrae en el metodo oncreate

        Bundle extras= getIntent().getExtras();
        Correop=extras.getString("correo");
        contraseñap=extras.getString("contraseña");

        tv.setText(Correop);

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

                Intent intent1 = new Intent(this, PerfilActivity.class);
                intent1.putExtra("correo",Correop);
                intent1.putExtra("contraseña",contraseñap);
                startActivity(intent1);

                break;
            case R.id.mCerrar:
                intent=new Intent(this,LoginActivity.class);
                intent.putExtra("correo",Correop);
                intent.putExtra("contraseña",contraseñap);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
