package com.example.cryptic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class outputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        Intent intent=getIntent();
        String msg=intent.getStringExtra("printable");
        char ch= intent.getCharExtra("crypt",'o');
        EditText ed=findViewById(R.id.out);
        ed.setText(msg);
        String title="Output Screen";
        if(ch=='e')
            title="Encrypted Value";
        else if(ch=='d')
            title="Decrypted Value";
        getSupportActionBar().setTitle(title);
    }
}