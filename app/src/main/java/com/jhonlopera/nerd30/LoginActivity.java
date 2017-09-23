package com.jhonlopera.nerd30;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private String correoR,contraseñaR, correo, contraseña,nombreR;
    private Uri urifoto;
    private EditText ecorreo, econtraseña;
    int duration = Toast.LENGTH_SHORT;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    String log;
    GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN =1035;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppEventsLogger.activateApp(LoginActivity.this);

        ecorreo = (EditText) findViewById(R.id.eCorreo);
        econtraseña = (EditText) findViewById(R.id.eContraseña);

        //-------------------------------------------------------
        //Si el logggin es con el registro de usuario
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
    //--------------------------------------------------------------------

        loginButton=(LoginButton) findViewById(R.id.login_button);
        callbackManager= CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        if (response.getError() != null) {

                        } else {
                            correoR = object.optString("email");
                            nombreR = object.optString("name");
                            Toast.makeText(getApplicationContext(),correoR,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender");
                request.setParameters(parameters);
                request.executeAsync();
                Profile profile=com.facebook.Profile.getCurrentProfile();
                urifoto=profile.getProfilePictureUri(400,400);
                log="facebook";

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("correo",correoR);
                intent.putExtra("nombre",nombreR);
                intent.putExtra("foto",urifoto.toString());
                intent.putExtra("log",log);
                startActivity(intent);
                finish();
            }
            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Login cancelado",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"Error en el login",Toast.LENGTH_SHORT).show();
            }});

        //Para loggin con google
        //-------------------------------------------------------------------------------------------
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                //.requestIdToken(getString(R.string.default_web_client_id))
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(),"Error en el loggin",Toast.LENGTH_SHORT);
                    }
                })

                //le pasamos lo que se le solicia a google (en este caso el acceso)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }});
        //-------------------------------------------------------------------------------------------
    }

    public void iniciar(View view) {

        correo = ecorreo.getText().toString();
        contraseña = econtraseña.getText().toString();

        if(correo.equals(correoR) && contraseña.equals(contraseñaR)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("correo",correoR);
            intent.putExtra("contraseña",contraseñaR);
            intent.putExtra("nombre",nombreR);
            startActivity(intent);
            finish();
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
        if (requestCode==1234 && resultCode==RESULT_OK){ //registro
            correoR=data.getExtras().getString("correo");
            contraseñaR=data.getExtras().getString("contraseña");
            nombreR=data.getExtras().getString("nombre");
            Toast.makeText(getApplicationContext(),nombreR,Toast.LENGTH_SHORT).show();
        }

        else if (requestCode == RC_SIGN_IN) {//login con google
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        else {//facebook
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            correoR=acct.getEmail().toString();//obtener email
            nombreR=acct.getDisplayName();
            log="google";
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("correo",correoR);
            intent.putExtra("nombre",nombreR);
            intent.putExtra("log",log);
            startActivity(intent);
            finish();

        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(getApplicationContext(),"Error en el loggin",Toast.LENGTH_SHORT);
        }
    }

    public void Registrarse(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivityForResult(intent,1234);
    }
    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


}
