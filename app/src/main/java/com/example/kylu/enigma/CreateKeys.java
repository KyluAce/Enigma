package com.example.kylu.enigma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.attr.value;

/**
 * Created by kylu on 21.01.17.
 */

public class CreateKeys extends Activity {

    public Button bLogOut;
    public FirebaseAuth firebaseAuth;
    public TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_keys);
        bLogOut = (Button) findViewById(R.id.logout);

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
    }
}
