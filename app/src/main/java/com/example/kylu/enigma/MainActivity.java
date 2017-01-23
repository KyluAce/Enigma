package com.example.kylu.enigma;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.R.attr.value;


public class MainActivity extends Activity {

    public Button bSign;
    public Button bCreate;
    public EditText EtLogin;
    public EditText EtPass;
    public ProgressDialog progressDialog;
    public FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        bSign = (Button) findViewById(R.id.sign);
        bCreate = (Button) findViewById(R.id.create);
        EtLogin = (EditText) findViewById(R.id.login);
        EtPass = (EditText) findViewById(R.id.pass);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            //logged
            Intent myIntent = new Intent(MainActivity.this, CreateKeys.class);
            myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        }





        bSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });


        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, CreateUser.class);
                myIntent.putExtra("key2", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    private void userLogin()
    {
        String login = EtLogin.getText().toString();
        String pass = EtPass.getText().toString();

        if(TextUtils.isEmpty(login))
        {
            Toast.makeText(MainActivity.this,
                    "Login can't be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(MainActivity.this,
                    "Password can't be empty", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(login,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            //registered complete
                            Toast.makeText(MainActivity.this,
                                    "Sign in successful", Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(MainActivity.this, CreateKeys.class);
                            myIntent.putExtra("key", value); //Optional parameters
                            MainActivity.this.startActivity(myIntent);

                        }
                        else
                            Toast.makeText(MainActivity.this,
                                    "Can't log in", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                    }
                });

    }
}
