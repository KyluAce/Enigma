package com.example.kylu.enigma;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends Activity {

    public Button bSign;
    public Button bCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bSign = (Button) findViewById(R.id.sign);
        bCreate = (Button) findViewById(R.id.create);


       // bSign.setOnClickListener();
    }
}
