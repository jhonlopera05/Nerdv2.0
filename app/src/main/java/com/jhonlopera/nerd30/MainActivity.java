package com.jhonlopera.nerd30;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity {

    private  String correoR,contraseñaR,nombreR,log,url_foto;
    int duration = Toast.LENGTH_SHORT;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Lo que se envia siempre s eextrae en el metodo oncreate

        Bundle extras= getIntent().getExtras();

        if (extras != null){
            log=extras.getString("log");

            if (log.equals("facebook")){
                correoR=extras.getString("correo");
                url_foto=extras.getString("foto");
                CharSequence text = correoR +" "+ nombreR;
                Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
            }

            else if (log.equals("google")){
                correoR=extras.getString("correo");
                nombreR=extras.getString("nombre");
                CharSequence text = correoR +" "+ nombreR;
            }

            else{
                correoR=extras.getString("correo");
                nombreR=extras.getString("nombre");
                contraseñaR =extras.getString("contraseña") ;
                CharSequence text = correoR +" "+ nombreR;
            }

        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener(){
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

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

                signOut(); //cerrar sesion en google
                LoginManager.getInstance().logOut();// cerrar sesion en facebook
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

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Context context = getApplicationContext();
                        CharSequence text = "Saliendo de google";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
    }
}