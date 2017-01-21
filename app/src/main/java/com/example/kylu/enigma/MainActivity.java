package com.example.kylu.enigma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.value;


public class MainActivity extends Activity {

    public Button bSign;
    public Button bCreate;
    public EditText EtLogin;
    public EditText EtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bSign = (Button) findViewById(R.id.sign);
        bCreate = (Button) findViewById(R.id.create);
        EtLogin = (EditText) findViewById(R.id.login);
        EtPass = (EditText) findViewById(R.id.pass);

        bSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, CreateKeys.class);
                myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
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
}
