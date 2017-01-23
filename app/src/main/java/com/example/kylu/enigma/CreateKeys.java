package com.example.kylu.enigma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.value;

/**
 * Created by kylu on 21.01.17.
 */

public class CreateKeys extends Activity {

    public Button bLogOut;
    public Button bSave;
    public Button bGenerate;
    public FirebaseAuth firebaseAuth;
    public TextView name;
    public EditText EtPublicKey;
    public EditText EtPrivateKey;
    public DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.create_keys);
        bLogOut = (Button) findViewById(R.id.logout);
        bSave = (Button) findViewById(R.id.save);
        bGenerate = (Button) findViewById(R.id.generate);
        EtPublicKey = (EditText) findViewById(R.id.publicKey);
        EtPrivateKey = (EditText) findViewById(R.id.privateKey);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null)
        {
            //logged
            Intent myIntent = new Intent(CreateKeys.this, MainActivity.class);
            myIntent.putExtra("key", value); //Optional parameters
            CreateKeys.this.startActivity(myIntent);
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        name = (TextView) findViewById(R.id.Name);
        name.setText("Welcome" + user.getEmail());

        bLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                finish();
                Intent myIntent = new Intent(CreateKeys.this, MainActivity.class);
                myIntent.putExtra("key", value); //Optional parameters
                CreateKeys.this.startActivity(myIntent);


            }
        });


        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveInfo();

            }
        });

        bGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //generateKeys();

            }
        });
    }

    private void saveInfo()
    {
        String publicKey = EtPublicKey.getText().toString();
        String privateKey = EtPrivateKey.getText().toString();

        UserKeys userKeys = new UserKeys(publicKey, privateKey);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userKeys);
        Toast.makeText(this, "Information Saver",Toast.LENGTH_LONG).show();
    }
}
