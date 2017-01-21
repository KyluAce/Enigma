package com.example.kylu.enigma;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.R.attr.value;

/**
 * Created by kylu on 21.01.17.
 */

public class CreateUser extends Activity implements View.OnClickListener {
    public Button bTCreate;
    public EditText EtLogin;
    public EditText EtEmail;
    public EditText EtPass;
    public EditText EtConfPass;
    public ProgressDialog progressDialog;
    public FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        firebaseAuth = FirebaseAuth.getInstance();
        bTCreate = (Button) findViewById(R.id.createe);
        EtLogin = (EditText) findViewById(R.id.login);
        EtEmail = (EditText) findViewById(R.id.email);
        EtPass = (EditText) findViewById(R.id.pass);
        EtConfPass = (EditText) findViewById(R.id.confpass);
        progressDialog = new ProgressDialog(this);

    }


    public void onClick(View view) {
        if (view == bTCreate) {
            if (EtPass.equals(EtConfPass)) {
                register();
            } else
                Toast.makeText(CreateUser.this,
                        "Passwords doesn't match", Toast.LENGTH_LONG).show();
        }
    }


    private void register()
    {
        String login = EtLogin.getText().toString().trim();
        String email = EtLogin.getText().toString().trim();
        String pass = EtLogin.getText().toString().trim();

        if(TextUtils.isEmpty(login))
        {
            Toast.makeText(CreateUser.this,
                    "Login can't be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(CreateUser.this,
                    "Email can't be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(CreateUser.this,
                    "Password can't be empty", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            //registered complete
                            Toast.makeText(CreateUser.this,
                                    "Registration successful", Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(CreateUser.this, MainActivity.class);
                            myIntent.putExtra("key", value); //Optional parameters
                            CreateUser.this.startActivity(myIntent);

                        }
                        else
                            Toast.makeText(CreateUser.this,
                                    "Try again", Toast.LENGTH_LONG).show();

                    }
                });
    }
}
